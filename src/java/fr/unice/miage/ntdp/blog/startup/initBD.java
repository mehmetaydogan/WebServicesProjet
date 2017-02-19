/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.blog.startup;

import fr.unice.miage.ntdp.blog.Article;
import fr.unice.miage.ntdp.blog.Comment;
import fr.unice.miage.ntdp.blog.Role;
import fr.unice.miage.ntdp.blog.Status;
import fr.unice.miage.ntdp.blog.UserStatus;
import fr.unice.miage.ntdp.blog.Users;
import fr.unice.miage.ntdp.blog.bean.ArticleFacade;
import fr.unice.miage.ntdp.blog.bean.CommentFacade;
import fr.unice.miage.ntdp.blog.bean.RoleFacade;
import fr.unice.miage.ntdp.blog.bean.UsersFacade;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author AGLIF / AYDOGAN
 */
@Singleton
@Startup
public class initBD {
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private RoleFacade roleFacade;
    @EJB
    private CommentFacade commentFacade;
    @EJB
    private ArticleFacade articleFacade;
        @PostConstruct
    public void initBD() {
        System.out.println("---      Base de données      -----");
        System.out.println("--- En cours d'initialisation -----");
        creerDonneesTest();
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
        System.out.println("---      Base de données      -----");
        System.out.println("---        Initialisée        -----");
    }
    
    public void creerDonneesTest() {
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
        System.out.println("---           Roles           -----");
        System.out.println("--- En cours d'initialisation -----");
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------") ;
        this.creerRoles();
        System.out.println("------        Roles         -------");
        System.out.println("------     Initialisés     --------");
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
        System.out.println("---           Users           -----");
        System.out.println("--- En cours d'initialisation -----");
        this.creerUsers();
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
        System.out.println("-----         Users         -------");
        System.out.println("-----      Initialisés      -------");
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
        System.out.println("---           Articles        -----");
        System.out.println("--- En cours d'initialisation -----");
        this.creerArticles();
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
        System.out.println("-----        Articles        ------");
        System.out.println("-----      Initialisés      -------");
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
        System.out.println("---         Commentaires       ----");
        System.out.println("--- En cours d'initialisation  ----");
        this.creerComments();
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
        System.out.println("---------   Commentaires  ---------");
        System.out.println("--------     Initialisés      -----");
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
    }

    private void creerRoles() {
        Role roleAdmin = new Role("Administrateur","Gère toute l'application");
        roleFacade.create(roleAdmin);
        Role roleUser = new Role("User","Ajout et suppression de ces articles, commente tous les articles.");
        roleFacade.create(roleUser);
        
        List<Role> roles = roleFacade.findAll();
        for(Role role : roles) {
            System.out.println("Role [ID =" + role.getId() + "; Name = " + role.getName()+"]");
        }
        /*Role roleAnonyme = new Role("Anonyme","Consultation des articles seulement.");
        roleFacade.create(roleAnonyme);*/
    }

    private void creerUsers() {
        Role roleAdmin = roleFacade.find(new Long("1"));
        Role roleUser = roleFacade.find(new Long("2"));
        Users userAdmin = new Users("AMOSSE","Eduard","Professeur de Web Services","-","eamosse","projet",new Date(),UserStatus.ENABLED,roleAdmin);
        usersFacade.create(userAdmin);
        Users userAdminDisabled = new Users("Admin Test","disabledAdmin","Professeur de Web Services","-","testAdmin","projet",new Date(),UserStatus.DISABLED,roleAdmin);
        usersFacade.create(userAdminDisabled);
        Users userAglif = new Users("AGLIF","Fatima Zahra","Etudiante en Master 2","-","fzaglif","projet",new Date(),UserStatus.ENABLED,roleUser);
        usersFacade.create(userAglif);
        Users userAydogan = new Users("AYDOGAN","Mehmet","Etudiant en Master 2","-","maydogan","projet",new Date(),UserStatus.ENABLED,roleUser);
        usersFacade.create(userAydogan);
        Users userDisabled = new Users("User Test","disabledUser","Etudiant en Master 2","-","testUser","projet",new Date(),UserStatus.DISABLED,roleUser);
        usersFacade.create(userDisabled);
        
        List<Users> users = usersFacade.findAll();
        for(Users user : users) {
            System.out.println("User [ID = " + user.getId() + ";Name = " + user.getFirstname()+"]");
        }
    }

    private void creerArticles() {
        Article articlePublie = new Article("Projet Web services","Article fatima zahra aglif projet web services J2EE JAX-RS authentification Sophia",new Date(),"On a réalisé le projet de Web services en J2EE et JAX-RS et authentification.","-",Double.parseDouble("3.225"),Double.parseDouble("5.263"),"Sophia",Status.PUBLISHED,usersFacade.find(new Long("5")));
        articleFacade.create(articlePublie);
        Article articlePublieDeux = new Article("Projet Web services","Article fatima zahra aglif projet web services J2EE JAX-RS authentification Sophia",new Date(),"On a réalisé le projet de Web services en J2EE et JAX-RS et authentification.","-",Double.parseDouble("3.225"),Double.parseDouble("5.263"),"Sophia",Status.PUBLISHED,usersFacade.find(new Long("5")));
        articleFacade.create(articlePublieDeux);
        Article articleEnAttente = new Article("Contexte","Article fatima zahra aglif test 1 Sophia",new Date(),"Ce mini projet consiste à réaliser une application de Blog permettant à des utilisateurs de\n" +
                                                "créer, partager, commenter des articles en ligne. Votre travail consiste à étudier une\n" +
                                                "...","-",Double.parseDouble("3.225"),Double.parseDouble("5.263"),"Sophia",Status.WAITFORVALIDATION,usersFacade.find(new Long("5")));
        articleFacade.create(articleEnAttente);
        Article articleEnAttenteDeux = new Article("Application cliente","Article fatima zahra aglif test 1 Sophia",new Date(),"L'application cliente consiste à developper des interfaces graphiques permettant de\n" +
                                        "gérer les ressources de l'application.....","-",Double.parseDouble("3.225"),Double.parseDouble("5.263"),"Sophia",Status.WAITFORVALIDATION,usersFacade.find(new Long("5")));
        articleFacade.create(articleEnAttenteDeux);
        Article articleReport = new Article("Application serveur","Article fatima zahra aglif test 2 Sophia",new Date(),"Application déployée sur un serveur d'application gérant la base de données\n" +
                                                "du projet. Elle comprend deux grandes parties","-",Double.parseDouble("3.225"),Double.parseDouble("5.263"),"Sophia",Status.REPORTASABUSED,usersFacade.find(new Long("5")));
        articleFacade.create(articleReport);
        List<Article> articles = articleFacade.findAll();
        for(Article article : articles) {
            System.out.println("Article ID " + article.getId() + " title = " + article.getTitle());
        }
    }

    private void creerComments() {
        Comment premierCommentaireArticle = new Comment("First comment.",new Date(),usersFacade.find(new Long("6")),articleFacade.find(new Long("9")));
        commentFacade.create(premierCommentaireArticle);
         Comment deuxiemeCommentaireArticle = new Comment("It's serious 'mini projet'. Please do normal comments.",new Date(),usersFacade.find(new Long("5")),articleFacade.find(new Long("9")));
        commentFacade.create(deuxiemeCommentaireArticle);
        Comment troisiemeCommentaireArticle = new Comment("Ok i love you.",new Date(),usersFacade.find(new Long("6")),articleFacade.find(new Long("9")));
        commentFacade.create(troisiemeCommentaireArticle);
    }
}

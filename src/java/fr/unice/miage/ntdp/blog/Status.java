/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.blog;

/**
 *
 * @author AGLIF / AYDOGAN
 */
public enum Status {
    PUBLISHED (0), 
    REPORTASABUSED(1), 
    WAITFORVALIDATION(2); 

    private Status(int status) {
        this.status= status;
    }
    int status;
}

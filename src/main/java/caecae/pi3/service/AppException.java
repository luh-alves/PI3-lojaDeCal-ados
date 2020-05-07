/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caecae.pi3.service;

/**
 *
 * @author Felipe
 */
public class AppException extends Exception {
    public AppException(String msg, Throwable e) {
        super(msg, e);
    }
}

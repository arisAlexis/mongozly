package org.mongozly.server.rest.nio;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import org.mongodb.morphia.annotations.Embedded;

/**
 *
 * @author aris
 */
 @Embedded
 public class SocialAccount {

 public static SocialAccount findByProvider(SocialProvider provider, List<SocialAccount> list) {
     for (SocialAccount sa : list) {
         if (sa.getProvider() == provider) {
             return sa;
         }
     }
     return null;
 }

    public enum SocialProvider {

        TWITTER, FACEBOOK, GOOGLE
    }

    private SocialProvider provider;
    private String userName;

    public SocialProvider getProvider() {
        return provider;
    }

    public void setProvider(SocialProvider provider) {
        this.provider = provider;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

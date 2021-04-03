package com.oops;

import java.util.*;

class User{

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

class Chatroom{
    private String name;
    private Set<String> username;
    private List<String> messages;

    {
        name = "";
        username = new HashSet<String>();
        messages = new ArrayList<String>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessages(String name){
        this.messages.add(name);
    }
    public ArrayList<String> getMessages(){
        return (ArrayList<String>) messages;
    }

    public void setUsername(String uname){
        this.username.add(uname);
    }
    public HashSet<String> getUsername(){
        return (HashSet<String>) this.username;
    }

    public boolean removeUser(String username) {
        return this.username.remove(username);
    }
}

class ChatApplication {
Scanner s=new Scanner(System.in);
    private Map<String, Chatroom> chatrooms = new HashMap<String, Chatroom>();
    private Map<String, User> users = new HashMap<String, User>();
    private Set<String> loggedInUsers = new HashSet<String>();

    public boolean isChatroomNameValid(String name) {
        return chatrooms.containsKey(name);
    }

    public boolean isUsernameExists(String username) {
        return users.containsKey(username);
    }

    public boolean authenticateUser(String username, String password) {
        if(users.containsKey(username)){
            String pass=users.get(username).getPassword();
            return  pass.equals(password);
        }else {
            return false;
        }
    }

    //UI Methods Below
    public void createChatroom() {
        Chatroom newroom=new Chatroom();
        System.out.println("Enter Chatroom Name");
        String name=s.next();
        if(!isChatroomNameValid(name)){
            newroom.setName(name);
            chatrooms.put(name,newroom);
        }else{
            System.out.println("chatroom already exists");
        }

    }

    public void addNewUser() {
        User newuser=new User();
        System.out.println("Enter Chatroom name");
        String cname=s.next();
        if(isChatroomNameValid(cname)){
            System.out.println("Enter Username");
            String name=s.next();
            if(!isUsernameExists(name)){
                newuser.setUsername(name);
                System.out.println("Enter FirstName");
                newuser.setFirstName(s.next());
                System.out.println("Enter Lastname");
                newuser.setLastName(s.next());
                System.out.println("Enter Password");
                newuser.setPassword(s.next());
                users.put(newuser.getUsername(), newuser);
            }
            chatrooms.get(cname).setUsername(name);

        }else {
            System.out.println("chatroom does not exist");
        }

    }

    public void login() {
        System.out.println("Enter Username");
        String uname=s.next();
        if(this.loggedInUsers.contains(uname)){
            System.out.println("User already logged in");
            return;
        }
        System.out.println("Enter Password");
        String pass=s.next();
        if(authenticateUser(uname,pass)){
            this.loggedInUsers.add(uname);
        }else {
            System.out.println("Incorrect Username or Password");
        }


    }

    public void sendMessage() {
        System.out.println("Enter Chatroom name");
        String name=s.next();
        if(chatrooms.containsKey(name)){
            System.out.println("Enter Message");
            chatrooms.get(name).setMessages(s.next());
        }

    }
    public void printMessages() {
        System.out.println("Enter Chatroom name");
        String name=s.next();
        if(isChatroomNameValid(name)){
            ArrayList<String> display=chatrooms.get(name).getMessages();
            for (String m:display
                 ) {
                System.out.println(m);
            }
        }

    }

    public void listUsersFromChatroom() {
        System.out.println("Enter Chatroom Name");
        String cname=s.next();
        for (String s : this.chatrooms.get(cname).getUsername()) {
            System.out.println(s);
        }
    }

    public void logout(){
        System.out.println("Enter Username");
        String name=s.next();
        this.loggedInUsers.remove(name);
    }

    public void deleteUser(){
        System.out.println("Enter Chatroom name");
        String cname=s.next();
        System.out.println("Enter Username");
        String name=s.next();
        this.chatrooms.get(cname).removeUser(name);
        users.remove(name);
    }
    public void removeChatroom() {
        System.out.println("Enter Chatroom name");
        String cname=s.next();
        this.chatrooms.remove(cname);
    }

    public void menu() {
        System.out.println("A) Create a chatroom");
        System.out.println("B) Add the user");
        System.out.println("C) User login");
        System.out.println("D) Send a message");
        System.out.println("E) Display the messages from a specific chatroom");
        System.out.println("F) List down all users belonging to the specified chat room.");
        System.out.println("G) Logout");
        System.out.println("H) Delete an user");
        System.out.println("I) Delete the chat room.");
        System.out.println("Please enter your option:");
    }
}

public class Assignment3Q6{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        ChatApplication item=new ChatApplication();
        System.out.println(">java ChatApplication");
        System.out.println("Options:");
        while(true){
            item.menu();
            char ch=s.next().charAt(0);
            switch (ch){
                case 'A':
                    item.createChatroom();
                    break;
                case 'B':
                    item.addNewUser();
                    break;
                case 'C':
                    item.login();
                    break;
                case 'D':
                    item.sendMessage();
                    break;
                case 'E':
                    item.printMessages();
                    break;
                case 'F':
                    item.listUsersFromChatroom();
                    break;
                case 'G':
                    item.logout();
                    break;
                case 'H':
                    item.deleteUser();
                    break;
                case 'I':
                    item.removeChatroom();
                    break;
            }
        }


    }
}

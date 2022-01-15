package com.univpm.po.NutritionStats.model;

import com.univpm.po.NutritionStats.utility.Serialization;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

public class Mailbox implements Serializable {
    public final static String DIR = "mailbox/";
    public final static String DROPBOX_DIR = "/mailbox/";

    TreeMap<LocalDate, ArrayList<Message>> mailbox;

    public Mailbox() {
        this.mailbox = new TreeMap<>();
    }

    public TreeMap<LocalDate, ArrayList<Message>> getMailbox() {
        return mailbox;
    }

    public Mailbox sendMessage(Message message) {
        if (mailbox.containsKey(LocalDate.now()))
            mailbox.get(LocalDate.now()).add(message);
        else
            mailbox.put(LocalDate.now(), new ArrayList<>() {{
                add(message);
            }});
        return this;
    }

    public Mailbox sendMessage(Message message, LocalDate date) {
        if (mailbox.containsKey(LocalDate.now()))
            mailbox.get(date).add(message);
        else
            mailbox.put(date, new ArrayList<>() {{
                add(message);
            }});
        return this;
    }

    public void save(String token) {
        Serialization s = new Serialization(DIR, token + ".dat");
        s.saveObject(this);
    }
}

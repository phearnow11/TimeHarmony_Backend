package com.example.TimeHarmony.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.dtos.Favorites;
import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.CartService;
import com.example.TimeHarmony.service.EmailService;
import com.example.TimeHarmony.service.MemberService;
import com.example.TimeHarmony.service.StringService;
import com.example.TimeHarmony.service.WatchService;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService MEMBER_SERVICE;

    @Autowired
    private EmailService EMAIL_SERVICE;

    @Autowired
    private StringService STRING_SERVICE;

    @Autowired
    private CartService CART_SERVICE;

    @Autowired
    private WatchService WATCH_SERVICE;

    private final String DEFAULT_MAIL_SUBJECT_VERIFY_GOOGLE = "Email Verification Code";
    private final String DEFAULT_MAIL_SUBJECT_VERIFY_PASSWORD = "Password Changing Verification Code";

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Optional<Members> getMember(@PathVariable("id") String member_id) {
        return MEMBER_SERVICE.getMemberbyID(member_id);
    }

    @RequestMapping(value = "get/addresses/{id}", method = RequestMethod.GET)
    public List<Addresses> getAddresses(@PathVariable("id") String member_id) {
        return MEMBER_SERVICE.getAddresses(member_id);
    }

    @RequestMapping(value = "verify/{type}/getcode/{id}", method = RequestMethod.GET)
    public String updateEmailVerificationCode(@PathVariable("id") String member_id, @PathVariable("type") String type) {
        String code = STRING_SERVICE.autoGenerateString(6);
        Members user = MEMBER_SERVICE.getMemberbyID(member_id).get();

        if (user.getEmail().isEmpty())
            return "User does not have an email";

        String subject_mail = "";
        switch (type) {
            case "google":
                subject_mail = DEFAULT_MAIL_SUBJECT_VERIFY_GOOGLE;
                break;
            case "password":
                subject_mail = DEFAULT_MAIL_SUBJECT_VERIFY_PASSWORD;
                break;
            default:
                break;
        }

        EMAIL_SERVICE.sendSimpleMessage(user.getEmail(), subject_mail, code);
        return code;
    }

    @RequestMapping(value = "update/email/{id}", method = RequestMethod.PUT)
    public String updateEmail(@PathVariable("id") String member_id,
            @RequestParam("email") String new_email) {

        return MEMBER_SERVICE.updateEmail(member_id, new_email);
    }

    @RequestMapping(value = "update/history{id}", method = RequestMethod.POST)
    public String updateHistories(@PathVariable("id") String id, @RequestBody Map<String, List<String>> data) {
        List<String> urls = data.get("url");
        List<Timestamp> times = new ArrayList<>();
        for (String i : data.get("access_time")) {
            times.add(Timestamp.valueOf(i));
        }
        return MEMBER_SERVICE.updateAccessHistories(id, urls, times);
    }

    @RequestMapping(value = "save/address", method = RequestMethod.POST)
    public Addresses saveAddresses(@RequestBody Map<String, String> data, @PathVariable("id") String member_id) {
        Members cur_m = MEMBER_SERVICE.getMemberbyID(member_id).get();
        Addresses nAdd = new Addresses(data.get("address_id"), cur_m, data.get("name"),
                data.get("phone"), data.get("detail"), Boolean.valueOf(data.get("default")));
        return MEMBER_SERVICE.addAddress(nAdd);
    }

    @RequestMapping(value = "add/to-cart/{id}", method = RequestMethod.POST)
    public String addToCart(@PathVariable("id") String member_id, @RequestParam("watch_id") String watch_id) {
        if (CART_SERVICE.addToCart(watch_id, member_id) != null) {
            return "Cart Added";
        }
        return "Failed to add to cart";
    }

    @RequestMapping(value = "to-seller", method = RequestMethod.POST)
    public String saveSeller(@RequestParam("id") String id, @RequestParam("username") String username) {

        return MEMBER_SERVICE.toSeller(id, username);
    }

    @RequestMapping(value = "add/favories/{id}", method = RequestMethod.POST)
    public String addFavories(@PathVariable("id") String member_id, @RequestBody Map<String, List<String>> data) {
        List<String> watchList = data.get("w_ids");
        return MEMBER_SERVICE.addFavorites(member_id, watchList);
    }

    @RequestMapping(value = "get/favorites/{id}", method = RequestMethod.GET)
    public List<Watch> getFavorites(@PathVariable("id") String member_id) {
        List<String> wList = new ArrayList<>();
        for (Favorites i : MEMBER_SERVICE.getFavoritesFromMember(member_id))
            wList.add(i.getWatch_id());

        return WATCH_SERVICE.getWatchesFromWatchID(wList);
    }
}

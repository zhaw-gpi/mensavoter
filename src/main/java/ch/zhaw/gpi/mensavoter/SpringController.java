package ch.zhaw.gpi.mensavoter;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringController {

    @Autowired
    private MenuRepository menuRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "redirect:/MensaVoter";
    }

    @RequestMapping(value = "/MensaVoter", method = RequestMethod.GET)
    public String init(@ModelAttribute("model") ModelMap model) {
        
        model.addAttribute("menus", menuRepository.findAll());
        return "index";
    }

    
    @RequestMapping(value = "/menu/{title}/likes", method = RequestMethod.GET)
    @ResponseBody
    public Integer getLikes(@PathVariable String title) {
        return menuRepository.findById(title).get().getLikes();
    }

    @RequestMapping(value = "/menu/{title}/comments", method = RequestMethod.GET)
    @ResponseBody
    public String getComments(@PathVariable String title) {
        return menuRepository.findById(title).get().getComments();
    }

    @RequestMapping(value = "/menu/{title}/likes", method = RequestMethod.PATCH)
    public ResponseEntity<HttpStatus> addLike(@PathVariable String title) {
        menuRepository.findById(title).get().like();
        menuRepository.flush();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/menu/{title}/comments", method = RequestMethod.PATCH)
    public ResponseEntity<HttpStatus> addComment(@PathVariable String title, @RequestParam Map<String, String> requestBody) {
        String comment = requestBody.get("comment");
        menuRepository.findById(title).get().addComment(comment);
        menuRepository.flush();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

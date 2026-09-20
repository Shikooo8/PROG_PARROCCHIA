package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.parrocchiatrasfigurazione.sito_backend.model.Notizia;
import it.parrocchiatrasfigurazione.sito_backend.service.NotiziaService;
import jakarta.validation.Valid;

@Controller
public class NotiziaController {

    private NotiziaService notiziaService;

    public NotiziaController(NotiziaService notiziaService) {
        this.notiziaService = notiziaService;
    }

    @GetMapping("/notizie")
    public String list(Model model) {
        model.addAttribute("notizie", notiziaService.getTutteLeNotizie());
        return "notizie/list";
    }

    @GetMapping("/notizia/{id}")
    public String mostraNotizia(@PathVariable Long id, Model model) {
        model.addAttribute("notizia", notiziaService.getNotizia(id));
        model.addAttribute("pagina", "notizie");
        return "notizie/show";
    }


//========================= ADMIN ================================

      @GetMapping("/admin/notizie/nuovo")
    public String showNewNotiziaForm( Model model) {
        model.addAttribute("notizia", new Notizia());
        return "admin/notizie/form";
    }


    @PostMapping("/admin/notizie/nuovo")
    public String saveNewNotizia(@Valid @ModelAttribute ("notizia") Notizia notizia, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/notizie/form";
        }
      //  try{
            notiziaService.save(notizia);
            return "redirect:/admin/notizie";
        /*} catch(DuplicateNotiziaException e){
            bindingResult.reject("notizia.duplicate", e.getMessage()); 
            return "admin/notizie/form";

        }*/
    }


     @GetMapping("/admin/notizie/{id}/modifica")
    public String showExistingNotiziaForm(@PathVariable Long id, Model model) {
        model.addAttribute("notizia", notiziaService.getNotizia(id));
                return "admin/notizie/form";
    }

    @PostMapping("/admin/notizie/{id}/modifica")
    public String saveExistentEvent(@PathVariable Long id, @Valid @ModelAttribute ("notizia") Notizia notizia, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "admin/notizie/form";
        }
     //   try{
            notizia.setId(id);
            this.notiziaService.save(notizia);
            return "redirect:/admin/notizie";

/*} catch(DuplicateNotiziaException e){
            bindingResult.reject("notizia.duplicate"); 
            return "admin/notizie/form";
        }
 */
        
    }

    @PostMapping("/admin/notizie/{id}/cancella")
    public String deleteEvent(@PathVariable Long id) {
        notiziaService.delete(id);
        return "redirect:/admin/notizie";
    }
    
    
}
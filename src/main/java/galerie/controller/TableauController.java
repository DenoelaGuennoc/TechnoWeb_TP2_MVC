/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.controller;

import galerie.dao.TableauRepository;
import galerie.entity.Artiste;
import galerie.entity.Galerie;
import galerie.entity.Tableau;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Denoëla
 */
public class TableauController {
    
    @Autowired
    private TableauRepository tableauDAO;
    
    /**
     * Affiche toutes les catégories dans la base
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('afficheTableau.html')
     */
    @GetMapping(path = "show")
    public String afficheTousLesTableaux(Model model) {
        model.addAttribute("tableau", tableauDAO.findAll());
        return "afficheTableau";
    }
    
    /**
     * Montre le formulaire permettant d'ajouter une galerie
     *
     * @param tableau initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @return le nom de la vue à afficher ('formulaireTableau.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("tableau") Tableau tableau, Model model) {
        HashSet<Artiste> auteurs = new HashSet<>();
        for(Tableau tableaux : tableauDAO.findAll()) {
            if (tableaux.getAuteur() != null) {
                auteurs.add(tableaux.getAuteur());
            }  
        }
        model.addAttribute("auteurs", auteurs);
        return "formulaireTableau";
    }
    
}

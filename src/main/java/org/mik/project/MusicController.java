package org.mik.project;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MusicController {
	private  final static int DEFAULTS = 5;
	@Autowired
	MusicRepository MusicRepository;

	@GetMapping("/")
	public String index( Model model) {
		model.addAttribute("musics", MusicRepository.findAll());
		return "index";
	}
	@GetMapping("/index")
	public String index1( Model model) {
		model.addAttribute("musics", MusicRepository.findAll());
		return "index";
	}

	@GetMapping("/signup")
	public String showSignUpForm(Music music) {
		return "add-music";
	}

	@PostMapping("/addmusic")
	public String addUser(@Valid Music music, BindingResult result, Model model) {
		if (result.hasErrors())
			return "add-music";

		MusicRepository.save(music);
		model.addAttribute("musics", MusicRepository.findAll());
		return "index";// additional CRUD methods
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Music music = MusicRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid music Id:" + id));
		model.addAttribute("music", music);
		return "update-music";
	}

	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid Music music, BindingResult result, Model model) {
		if (result.hasErrors()) {
			music.setId(id);
			return "update-music";
		}

		MusicRepository.save(music);
		model.addAttribute("musics", MusicRepository.findAll());
		return "index";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model, HttpServletResponse resp) {
		if (id < DEFAULTS) {
			return "error/403";
		}
		Music music = MusicRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid music Id:" + id));
		MusicRepository.delete(music);
		model.addAttribute("musics", MusicRepository.findAll());
		return "index";
	}
	

}

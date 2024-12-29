package com.cup.spring.aop;

import java.util.NoSuchElementException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AdviceControllerException {

	@ExceptionHandler(NullPointerException.class)
	public @ResponseBody String nullpoint(NullPointerException e, Model model) {
		log.info("NullPointerException:{}", e.getMessage());
		model.addAttribute("message", e.getMessage());
		return "adm/error-500";
	}
	@ExceptionHandler(NumberFormatException.class)
	public @ResponseBody String numberformat(NumberFormatException e, Model model) {
		log.info("NumberFormatException:{}", e.getMessage());
		model.addAttribute("message", e.getMessage());
		return "adm/error-500";
	}
	@ExceptionHandler(IllegalStateException.class)
	public @ResponseBody String illegal(IllegalStateException e, Model model) {
		log.info("IllegalStateException:{}", e.getMessage());
		model.addAttribute("message", e.getMessage());
		return "adm/error-500";
	}
	@ExceptionHandler(NoSuchElementException.class)
	public @ResponseBody String nosuchelement(NoSuchElementException e, Model model) {
		log.info("NoSuchElementException:{}", e.getMessage());
		model.addAttribute("message", e.getMessage());
		return "adm/error-500";
	}

}


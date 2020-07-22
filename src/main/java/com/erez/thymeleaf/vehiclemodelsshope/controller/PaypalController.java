package com.erez.thymeleaf.vehiclemodelsshope.controller;


import com.erez.thymeleaf.vehiclemodelsshope.entity.PayPalPaymentDTO;
import com.erez.thymeleaf.vehiclemodelsshope.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaypalController {
	
	@Autowired
	PaypalService payPalservice;

	public static final String SUCCESS_URL = "/pay/success";
	public static final String CANCEL_URL = "/pay/cancel";
	public static final String BASE_URL = "http://localhost:8080";

	@GetMapping("/pay/showPayForm")
	public String showPayForm(Model theModel) {
		theModel.addAttribute("order", new PayPalPaymentDTO());
		return "pay/pay-form";
	}

	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") PayPalPaymentDTO order) {
		try {
			Payment payment = payPalservice.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(), BASE_URL + CANCEL_URL,BASE_URL + SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	 @GetMapping("pay/cancel")
	    public String cancelPay() {
	        return "pay/cancel-payment";
	    }

	    @GetMapping("pay/success")
	    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
	        try {
	            Payment payment = payPalservice.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	                return "pay/success-payment";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "redirect:/";
	    }


}

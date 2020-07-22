package com.erez.thymeleaf.vehiclemodelsshope.controller;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Address;
import com.erez.thymeleaf.vehiclemodelsshope.entity.Customer;
import com.erez.thymeleaf.vehiclemodelsshope.entity.SalesRepresentative;
import com.erez.thymeleaf.vehiclemodelsshope.exception.NoRecordFoundException;
import com.erez.thymeleaf.vehiclemodelsshope.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/customers")

public class CustomrController {

	private CustomerService customerService;
	private SalesRepresentativeService salesRepresentativeService;
	private CountryService countryService;
	private StateService stateService;
	private CityService cityService;
	private String counttryName;
	private String stateName;

	@Autowired
	public CustomrController(CustomerService theCustomerService,
			SalesRepresentativeService theSalesRepresentativeService, 
			CountryService theCountryService,StateService theStateService,
			CityService theCityService) {

		customerService = theCustomerService;
		salesRepresentativeService = theSalesRepresentativeService;
        countryService = theCountryService;
        stateService = theStateService;
        cityService = theCityService;
       	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	
	@GetMapping("/showFormForAddCustomer")
	public String showFormForAddCustomer(Model theModel ) {

		Customer theCustomer = new Customer();
		theCustomer.setAddress(new 	Address());
		theCustomer.setSalesRepresentative(new SalesRepresentative());
		

		theModel.addAttribute("customer", theCustomer);
		theModel.addAttribute("salesRepresentatives", salesRepresentativeService.getSalesRepresentatives());
		theModel.addAttribute("countries", countryService.findAll());
				
		return "customers/customer-form";
	}


	

	@GetMapping("/list")
	public String listCustomrs(Model theModel ,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(6);

		Page<Customer> customerPage = customerService.getAllCustomersPaginated(PageRequest.of(currentPage - 1, pageSize));

		int totalPages = customerPage.getTotalPages();


		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			theModel.addAttribute("pageNumbers", pageNumbers);
		}

		theModel.addAttribute("customerPage", customerPage);


		return "customers/list-customers";
	}



	@GetMapping("/search")
	public String searchCustomerByName(@RequestParam("customerName") String theName, Model theModel,
                                       @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(2);

		Page<Customer> customerPage =
				customerService.searchCustomerByName(theName, PageRequest.of(currentPage - 1, pageSize));

		int totalPages = customerPage.getTotalPages();

		if (totalPages > 0) {
			List<Integer> pageNumbers = 
					IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			theModel.addAttribute("pageNumbers", pageNumbers);
		}

		theModel.addAttribute("customerPage", customerPage);

		return "customers/list-customers";

	}


	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("customer")  @Valid Customer theCustomer ,
                               BindingResult bindingResult , Model theModel) {

		
		Address theAddress = Optional.ofNullable(theCustomer.getAddress()).orElse(( new Address() ));
		theCustomer.setAddress(theAddress);
		theCustomer.getAddress().setCountry(counttryName);
		theCustomer.getAddress().setState(stateName);
		
		if (bindingResult.hasErrors()) {
           
			theAddress = Optional.ofNullable(theCustomer.getAddress()).orElse(( new Address() ));
			theCustomer.setAddress(theAddress);
			theCustomer.getAddress().setCountry(counttryName);
			theCustomer.getAddress().setState(stateName);
						
			SalesRepresentative Salesrep = Optional.ofNullable(theCustomer.getSalesRepresentative()).orElse(( new SalesRepresentative()  ));
			theCustomer.setSalesRepresentative(Salesrep);
			
		
			theModel.addAttribute("salesRepresentatives", salesRepresentativeService.getSalesRepresentatives());
			theModel.addAttribute("countries", countryService.findAll());
			theModel.addAttribute("customer",theCustomer);
			return "customers/customer-form";

		}
		
		
		customerService.saveCustomer(theCustomer);
		  
		   return "redirect:/customers/list";
	}

	
	
	@ExceptionHandler({ Exception.class })
	@GetMapping("/showFormForUpdatCustomer")
	public String showFormForUpdateCustomer(@RequestParam("customerId") Integer  customerId , Model theModel ) {
		
		Customer theCustomer;
		try {
			theCustomer = customerService.getCustomer(customerId);

			Address theAddress = Optional.ofNullable(theCustomer.getAddress()).orElse(( new Address() ));
			theCustomer.setAddress(theAddress);
			
			SalesRepresentative Salesrep = Optional.ofNullable(theCustomer.getSalesRepresentative()).orElse(( new SalesRepresentative()  ));
			theCustomer.setSalesRepresentative(Salesrep);
			theModel.addAttribute("salesRepresentatives", salesRepresentativeService.getSalesRepresentatives());
			theModel.addAttribute("countries", countryService.findAll());
			theModel.addAttribute("customer",theCustomer);
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "customers/customer-form";
	}
	

	@ResponseBody
	@RequestMapping(value={"/showFormForUpdatCustomer/country/{id}/{name}" ,
			                "/country/{id}/{name}",
			                 "/save/country/{id}/{name}"}
	                         , method = RequestMethod.GET)
	public String loadStatesByCountry(@PathVariable Integer id, @PathVariable String name) {
		counttryName ="";
		Gson gson = new Gson();
		counttryName =name;
		return gson.toJson(stateService.findByCountryId(id));
	}


	
    @RequestMapping(value= "/customerNameAutocomplete")
    @ResponseBody
   public List<String> customerNameAutocomplete( @RequestParam(value = "term", required = false ,defaultValue="") String term){
	
	 return null;
	 
 }


       @ResponseBody
	   @RequestMapping(value = {"state/{id}/{name}",
			                 "showFormForUpdatCustomer/state/{id}/{name}",
			                 "/save/state/{id}/{name}" } , method = RequestMethod.GET	)
  	     public String loadCitiesByState(@PathVariable Integer id, @PathVariable String name) {
		      stateName ="";
		      System.out.println("State name and id: "+ " " + id +"   " + name);
		      stateName = name;
		      Gson gson = new Gson();
		      return gson.toJson(cityService.findByStateId(id));
}


//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	@ResponseBody
//	public  String delete(@RequestParam Integer customerId, @RequestParam String customerName , @RequestParam String status) {
//		    Gson gson = new Gson();
//		    Response customerResponse = new Response();
//		    customerResponse.setCustomerId(customerId);
//		    customerResponse.setCustomerName(customerName);
//
//		try {
//
//			 customerService.deleteCustomer(customerId);
//			 customerResponse.setStatus("Done");
//
//		}
//		catch (NoRecordFoundException nr) {
//			  customerResponse.setStatus("Error");
//			 return gson.toJson(customerResponse);
//		}
//
//		return gson.toJson(customerResponse);
//
//
//	}

//	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
//	public @ResponseBody Response deleteCustomerAjax( @RequestBody Response customerResponse) {
//		try {
//			  customerService.deleteCustomer(customerResponse.getCustomerId());
//			  customerResponse.setStatus("Done");
//		}
//		catch (NoRecordFoundException nr) {
//			customerResponse.setStatus("Error");
//			return customerResponse;
//		}
//		return customerResponse;
//	}

//	@ResponseBody
//	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
//	public String deleteCustomer(@PathVariable("id") Integer theId) {
//        Gson gson = new Gson();
//		Response customerResponse = new Response();
//		try {
//			customerService.deleteCustomer(theId);
//			customerResponse.setStatus("Done");
//
//		}
//		catch (NoRecordFoundException nr) {
//			customerResponse.setStatus("Error");
//			customerResponse.setCustomerId(theId);
//
//
//		}
//		return gson.toJson(customerResponse);
//	}
//
//	@ResponseBody
//	@DeleteMapping(value = "/delete/{id}")
//	public String deleteCustomer(@PathVariable Integer id) {
//		Gson gson = new Gson();
//		Response customerResponse = new Response();
//
//		try {
//
//			customerService.deleteCustomer(id);
//			customerResponse.setStatus("Done");
//
//
//		}
//		catch (NoRecordFoundException nr) {
//
//			customerResponse.setStatus("Error");
//			customerResponse.setCustomerId(id);
//
//
//		}
//		return gson.toJson(customerResponse);
//	}

       
       @PostMapping("/delete")
       public String  deleteCustomerById(@RequestParam( value = "theCustomerId") Integer theId, HttpServletRequest request) {

    	   
    	  
         try {

   			   customerService.deleteCustomer(theId);
   			

   		}
   		catch (NoRecordFoundException nr) {

   			nr.printStackTrace();

   		}
         String referer = request.getHeader("Referer");
         return "redirect:"+ referer;
		
       
	
   	

         
       }

}

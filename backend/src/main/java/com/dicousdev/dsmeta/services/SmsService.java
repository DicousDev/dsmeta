package com.dicousdev.dsmeta.services;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dicousdev.dsmeta.entities.Sale;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	private SaleService saleService;

	public void sendSms(Long idVendedor) {
		
		Optional<Sale> saleOptional = saleService.findSaleById(idVendedor);
		
		if(!saleOptional.isPresent()) {
			throw new RuntimeException("Vendedor não encontrado!");
		}
		
		Sale sale = saleOptional.get();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
		String date = dateFormatter.format(sale.getDate());
		String messageSMS = "Vendedor " + sale.getNomeVendedor() + " foi destaque em " + date + " com um total de R$ " + String.format("%.2f", sale.getTotal());
		
		
		Twilio.init(twilioSid, twilioKey);
		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
		Message message = Message.creator(to, from, messageSMS).create();
		System.out.println(message.getSid());
	}
}
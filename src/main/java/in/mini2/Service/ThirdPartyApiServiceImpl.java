package in.mini2.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.HttpMessageConverter;

import in.mini2.Model.Quote;

@Service
public class ThirdPartyApiServiceImpl implements ThirdPartyApiService{

	
	@Override
	public String getDataFromApi(String url , int index) {
		
	 RestTemplate rt = new RestTemplate();
			
	 List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		rt.setMessageConverters(messageConverters);
	    
		Quote[] quotes = rt.getForObject(url, Quote[].class);
	 
	    return quotes[index].getText();
			
	}

}

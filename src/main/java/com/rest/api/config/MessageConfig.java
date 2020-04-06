package com.rest.api.config;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import net.rakugakibox.util.YamlResourceBundle;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

	/**
	 * @apiNote Set session locale
	 * 
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.KOREAN);
		return slr;
	}

	/**
	 * @apiNote Change session locale
	 * 
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/**
	 * @apiNote Register intercepter on system registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	/**
	 * 
	 * @param basename
	 * @param encoding
	 * @return
	 */
	@Bean
	public MessageSource messageSource(
			@Value("${spring.messages.basename}") String basename,
			@Value("${spring.messages.encoding}") String encoding) {
		YamlMessageSource mSource = new YamlMessageSource();
		mSource.setBasename(basename);
		mSource.setDefaultEncoding(encoding);
		mSource.setAlwaysUseMessageFormat(true);
		mSource.setUseCodeAsDefaultMessage(true);
		mSource.setFallbackToSystemLocale(true);
		return mSource;
	}

	/**
	 * 
	 * @author lsh
	 *
	 */
	private static class YamlMessageSource extends ResourceBundleMessageSource {
		@Override
		protected ResourceBundle doGetBundle(String basename, Locale locale) {
			return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
		}
	}
}

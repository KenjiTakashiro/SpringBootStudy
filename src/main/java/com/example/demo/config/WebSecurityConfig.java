package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * セキュリティ設定を無視するリクエスト設定
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/img/**", "/css/**", "/js/**", "/webjars/**", "/font/**");
	}

	
	  @Override protected void configure(HttpSecurity http) throws Exception {
		  http.csrf()
		  	.disable()
		  	.authorizeRequests()
		  	.antMatchers("/", "/login")
		  	.permitAll()
		  	.anyRequest()
		  	.authenticated()
		  	.and()
		  	.formLogin()
		  	.loginPage("/login")
			.successForwardUrl("/myPage")//認証成功時のURL
		  	.usernameParameter("username")
		  	.passwordParameter("password")
		  	.failureUrl("/login")
		  	.defaultSuccessUrl("/myPage")
		  	.permitAll()
		  	.and()
		  	.logout()
	  // ログアウトでログインページに戻る 
		  	.logoutSuccessUrl("/login") // セッションを破棄する
	  .invalidateHttpSession(true).permitAll(); }
	 
	/*
	 * @Override
	 *  public void configure(HttpSecurity http)throws Exception {
	 * http.authorizeRequests() .antMatchers("/login").permitAll()//ログインフォームは認可
	 * .anyRequest().authenticated(); http.formLogin()
	 * .loginProcessingUrl("/login")	//ログインを処理するURL .loginPage("login")//ログイン画面のURL
	 * .failureUrl("/")	//認証失敗時のURL 
	 * .successForwardUrl("/myPage")	//認証成功時のURL
	 * .usernameParameter("username")//ユーザーのパラメータ
	 * .passwordParameter("password");//パスワードのパラメータ名 http.logout()
	 * .logoutUrl("")//ログアウト時のURL .logoutSuccessUrl("/"); }
	 */
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// ＤＢによる独自認証を行う
		auth.userDetailsService(userDetailsService);
	}

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}
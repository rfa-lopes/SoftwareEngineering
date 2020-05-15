package fct.unl.pt.instagramplus;

import static org.assertj.core.api.Assertions.assertThat;

import fct.unl.pt.instagramplus.Controllers.Accounts.AccountsControllerClass;
import fct.unl.pt.instagramplus.Controllers.Authenticator.AuthenticatorClass;
import fct.unl.pt.instagramplus.Controllers.Authenticator.AuthenticatorInterface;
import fct.unl.pt.instagramplus.Controllers.Messages.MessagesControllerClass;
import fct.unl.pt.instagramplus.Controllers.Messages.MessagesControllerInterface;
import fct.unl.pt.instagramplus.Controllers.Publications.PublicationsControllerClass;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InstagramplusApplicationTests {

	@Autowired
	private AuthenticatorClass authenticator;

	@Autowired
	private AccountsControllerClass accountsController;

	@Autowired
	private MessagesControllerClass messagesController;

	@Test
	void contextLoads() {
		assertThat(authenticator).isNotNull();
		assertThat(accountsController).isNotNull();
		assertThat(messagesController).isNotNull();
	}

}

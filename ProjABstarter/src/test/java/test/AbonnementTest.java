package test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sid.entities.Abonnement;
import org.sid.entities.AbonnementGSM;
import org.sid.entities.AbonnementInternet;
import org.sid.metier.IAbonnementMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbonnementTest {

	private IAbonnementMetier metier;

	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		metier = (IAbonnementMetier) ctx.getBean("metier");
	}

	@Test
	public void test() {
		List<Abonnement> abListBefore = metier.listAbonnements(true);
		metier.addAbonnement(new AbonnementGSM(new Date(), 7000, true, 400));
		metier.addAbonnement(new AbonnementInternet(new Date(), 4000, true, 8));
		List<Abonnement> abListAfter = metier.listAbonnements(true);
		Assert.assertTrue(abListBefore.size() + 2 == abListAfter.size());

	}

}

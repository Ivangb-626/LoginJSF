package nagusia;
import eredua.JPAUtil;
import eredua.domeinua.Erabiltzailea;
import eredua.domeinua.LoginGertaera;
import javax.persistence.EntityManager;
import java.util.*;
public class GertaerakDataAccess {
public GertaerakDataAccess() {}
public Erabiltzailea createAndStoreErabiltzailea(String izena, String pasahitza, String mota) {
EntityManager em = JPAUtil.getEntityManager();
Erabiltzailea e = new Erabiltzailea();
try {
em.getTransaction().begin();
e.setIzena(izena);
e.setPasahitza(pasahitza);
e.setMota(mota);
em.persist(e);
em.getTransaction().commit();
} catch (Exception ex) {
if (em.getTransaction().isActive()) {
em.getTransaction().rollback() ;}
throw ex;
} finally {em.close();}
return e; }
public LoginGertaera createAndStoreLoginGertaera(String erabiltzailea, boolean login, Date
data) {
EntityManager em = JPAUtil.getEntityManager();
LoginGertaera e = new LoginGertaera();
try {
em.getTransaction().begin();
e.setErabiltzailea((Erabiltzailea) em.find(Erabiltzailea.class, erabiltzailea));
e.setLogin(login);
e.setData(data);
em.persist(e);
em.getTransaction().commit();
} catch (Exception ex) {
System.out.println("Errorea: erabiltzailea ez da existitzen: " + ex.toString());
e = null;
if (em.getTransaction().isActive()) {
em.getTransaction().rollback(); }
} finally {em.close(); }
return e;
}
public static void main(String[] args) {
GertaerakDataAccess e = new GertaerakDataAccess();
System.out.println("Gertaeren sorkuntza:"); //
e.createAndStoreErabiltzailea("Ane", "125", "ikaslea");
e.createAndStoreLoginGertaera("Ane", true, new Date());
e.createAndStoreLoginGertaera("Ane", false, new Date());
e.createAndStoreErabiltzailea("Kepa", "126", "ikaslea");
e.createAndStoreLoginGertaera("Kepa", true, new Date());
e.createAndStoreLoginGertaera("Kepa", false, new Date());
List <Erabiltzailea> er=e.getErabiltzaileak();
System.out.println("3.1 => Erabiltzaileak:" + er);
List <LoginGertaera> lg=e.getLoginGertaerak();
System.out.println("3.1 => Login Gertaerak: "+lg);
}
public List<LoginGertaera> getLoginGertaerak() {
	 EntityManager em = JPAUtil.getEntityManager();
	List<LoginGertaera> result = new ArrayList<LoginGertaera>();
	try {
	 em.getTransaction().begin();
	 result = em.createQuery("from LoginGertaera", LoginGertaera.class).getResultList();
	 System.out.println("getLoginGertaerak() : "+result);
	 em.getTransaction().commit();
	 } catch (Exception e) {
	 if (em.getTransaction().isActive()) {
	 em.getTransaction().rollback();
	 }
	 throw e;
	 } finally {
	 em.close();
	 }
	 return result;
	 }
	public List<Erabiltzailea> getErabiltzaileak() {
	 EntityManager em = JPAUtil.getEntityManager();
	List<Erabiltzailea> result = new ArrayList<Erabiltzailea>();
	 try {
	 em.getTransaction().begin();
	 result = em.createQuery("from Erabiltzailea", Erabiltzailea.class).getResultList();
	 em.getTransaction().commit();
	 } catch (Exception e) {
	 if (em.getTransaction().isActive()) {
	 em.getTransaction().rollback();
	 }
	 throw e;
	 } finally {
	 em.close();
	 }
	 return result;
	 }
}
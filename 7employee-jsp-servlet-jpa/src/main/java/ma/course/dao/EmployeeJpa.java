package ma.course.dao;

import java.util.List;

import javax.persistence.EntityManager;

import ma.course.pojo.Employee;

public class EmployeeJpa {

	private EntityManager em=null;

	public EmployeeJpa(EntityManager em) {
		super();
		this.em = em;
	}
	
    public List<Employee> getAllEmployees() {
	    	//createQuery=> creer requete avec hql=manipuler les classes et les realtion entre les classe
		//select e from Employee e => premier e comme si vs ecrivez e*=tourt les employees, 2eme e => alias
		List<Employee> employees =em.createQuery("select e from Employee e", Employee.class)
			.getResultList();
		return employees;
	}
	
	public void createEmployee(int id, String firstName, String lastName, String department) {
		Employee employee=new Employee(id, firstName, lastName, department);
		//creer une transaction, begin=> demarrer une transaction
		em.getTransaction().begin();
		
		try {
			//persist => creer un objet/sauvegarder son etat dans db
			//va etre traduit par "insert into db object employee"
			em.persist(employee);
			//si tt marche bien on met commit
			em.getTransaction().commit();		
		}
		catch(Exception e) {
			e.printStackTrace();
			//si qlq chose ne marche^pas on met rollback
			em.getTransaction().rollback();	
		}
	}

	
	

}

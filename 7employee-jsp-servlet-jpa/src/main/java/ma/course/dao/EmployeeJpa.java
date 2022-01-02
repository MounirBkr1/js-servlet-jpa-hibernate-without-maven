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
		List<Employee> employees =em.createQuery("select e from Employee e", Employee.class)
			.getResultList();
		return employees;
	}
	
	public void createEmployee(int id, String firstName, String lastName, String department) {
		Employee employee=new Employee(id, firstName, lastName, department);
		em.getTransaction().begin();
		
		try {
			em.persist(employee);
			em.getTransaction().commit();		
		}
		catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();	
		}
	}

	
	

}

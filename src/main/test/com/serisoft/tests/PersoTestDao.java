package com.serisoft.tests;

import static org.junit.Assert.*;

import java.io.RandomAccessFile;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serisoft.dao.IPersoDao;
import com.serisoft.model.Personne;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appContext.xml")
public class PersoTestDao {

	
	@Autowired
	private IPersoDao dao;

	@Before
	public void setUp() {

		
	}

	
	@Test
	public void TestSave() {

		/*Personne p = new Personne();
		p.setNom("ALAILI");
		p.setPrenom("Karima");
		p.setMarie((short) 1);
		p.setNbenfants((short) 1);
		dao.save(p);
		System.out.println(p);
		
		assertTrue(p.getId() != null);*/
	}


	@Test
	public void TestUpdate() {

		Personne p= dao.findById(1);
		System.out.println(p);
		assertTrue(p.getNom().equals("ALLAL"));
		p.setPrenom("Mohammed Amene");
		dao.save(p);
		p= dao.findById(1);
		assertTrue(p.getPrenom().equals("Mohammed Amene"));
		
	}

	
	@Test
	public void testfindAll() {
		List<Personne> l = dao.findAll();
		System.out.println(l);
		assertTrue(l.size() > 0);
	}
}

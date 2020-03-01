package com.pratice.springboot.webservicemockitoTests.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratice.springboot.webservicemockitoTests.modal.Item;

//@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}

package com.sfdo.ngp.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.sfdo.ngp.data.schema.Processor;

public interface ProcessorRespository extends CrudRepository<Processor, Serializable> {

}

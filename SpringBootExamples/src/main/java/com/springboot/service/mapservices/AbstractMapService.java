package com.springboot.service.mapservices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springboot.domain.DomainObject;

public abstract class AbstractMapService {

	protected Map<Integer,DomainObject> domainMap;
	
	public AbstractMapService() {
		domainMap=new HashMap<>();
		loadDomainObjects();
	}
	
	public List<DomainObject> listAll() {
		return new ArrayList<>(domainMap.values());
	}

	public DomainObject getById(Integer id) {
		return domainMap.get(id);
	}

	public DomainObject saveOrUpdate(DomainObject domainObject) {
		if(domainObject==null)
			throw new RuntimeException("Domain Object is NULL");
		if(domainObject.getId()==null) {
			domainObject.setId(getNextKey());
		}
		domainMap.put(domainObject.getId(), domainObject);
		return domainObject;
	}

	public void delete(Integer id) {
		domainMap.remove(id);
		
	}

	public Integer getNextKey(){
		return Collections.max(domainMap.keySet())+1;
	}
	
	public abstract void loadDomainObjects();
}

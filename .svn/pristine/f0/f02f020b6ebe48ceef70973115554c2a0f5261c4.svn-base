package com.chinamobile.athena.risk.common.es;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import com.chinamobile.athena.risk.common.es.entity.EsBaseEntity;

public class EsTemplate extends ElasticsearchTemplate {
	private IndexQuery query;
	private Client client;
	private ResultsMapper resultsMapper;

	public EsTemplate(Client client) {
		super(client);
	}

	public void saveOrUpdate(EsBaseEntity esObj) {
		IndexQuery queryTemp  = new IndexQuery();
		queryTemp.setIndexName(query.getIndexName());
		queryTemp.setType(query.getType());
		queryTemp.setObject(esObj);
		queryTemp.setId(esObj.getEsId());
		super.putMapping(esObj.getClass());
		super.index(queryTemp);
		super.refresh(queryTemp.getIndexName(), true);
	}

	public void saveOrUpdate(List<EsBaseEntity> esObj) {
		List<IndexQuery> queryList = new ArrayList<IndexQuery>();
		for (EsBaseEntity esBaseEntity : esObj) {
			IndexQuery indexQuery = new IndexQuery();
			indexQuery.setId(esBaseEntity.getEsId());
			indexQuery.setObject(esObj);
			indexQuery.setIndexName(query.getIndexName());
			indexQuery.setType(query.getType());
			queryList.add(indexQuery);
		}
		super.putMapping(esObj.getClass());
		super.bulkIndex(queryList);
		super.refresh(query.getIndexName(), true);
	}

	public <T> T queryById(String id, Class<T> clazz) {
		GetResponse response = client
				.prepareGet(query.getIndexName(), query.getType(), id)
				.execute().actionGet();
		return resultsMapper.mapResult(response, clazz);
	}

	public void setQuery(IndexQuery query) {
		this.query = query;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setResultsMapper(ResultsMapper resultsMapper) {
		this.resultsMapper = resultsMapper;
	}

}

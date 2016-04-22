package org.itechart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class ElasticSearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchService.class);
    private static final String INDEX_NAME = "warehouse";

    private Client client;

    private ObjectMapper mapper;

    @PostConstruct
    private void init() throws UnknownHostException {
        client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        mapper = new ObjectMapper();
    }

    public void updateIndexes(Object entity, String indexType, String id) {
        byte[] json;
        try {
            json = mapper.writeValueAsBytes(entity);
        } catch (JsonProcessingException e) {
            LOGGER.error("JSON can not be prepared from source", e);
            return;
        }
        IndexResponse response = client.prepareIndex(INDEX_NAME, indexType, id).setSource(json).get();
        LOGGER.debug("Prepare Index Response: ", response);
    }

    @PreDestroy
    private void onShutdown() {
        client.close();
    }
}

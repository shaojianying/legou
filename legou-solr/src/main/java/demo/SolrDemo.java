package demo;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrDemo {
	@Test
	public void addSolr() throws SolrServerException, IOException {
		//solr���ݿ�
		SolrServer solrServer=new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
		//һ����¼
		SolrInputDocument document=new SolrInputDocument();
		document.addField("item_title", "基地科技电话空间都安静打咯京东");
		document.addField("id", "001");
		//�������ݿ�
		solrServer.add(document);
		solrServer.commit();
	}
}

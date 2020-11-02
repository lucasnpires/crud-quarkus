package com.github.lucasnpires;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.lucasnpires.model.entity.Produto;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@DBRider
@QuarkusTest
@QuarkusTestResource(value = DatabaseLifecycle.class)
public class ProdutoTest {
	
	@Test
	@DBUnit(schema = "public", caseInsensitiveStrategy = Orthography.LOWERCASE)
	@DataSet(value= "produtos1.yml")
	void testeCount() {
		Assert.assertEquals(1, Produto.count());		
	}

}

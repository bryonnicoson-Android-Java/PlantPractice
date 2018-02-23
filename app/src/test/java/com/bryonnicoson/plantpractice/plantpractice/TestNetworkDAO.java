package com.bryonnicoson.plantpractice.plantpractice;

import com.bryonnicoson.plantpractice.dao.NetworkDAO;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by bryon on 2/22/18.
 */

public class TestNetworkDAO {

    NetworkDAO networkDAO;

    @Before
    public void setup() {
        networkDAO = new NetworkDAO();
    }

    @Test
    public void fetchShouldSucceedWhenGivenValidURI() throws IOException {
        String result = networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=akjdf");
        System.out.println(result);
        assertEquals("{\"plants\":[]}-1", result);
    }
}

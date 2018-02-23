package com.bryonnicoson.plantpractice.plantpractice;

import com.bryonnicoson.plantpractice.dao.BDDTestPlantDAO;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by bryon on 2/22/18.
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({
        BDDTestPlantDAO.class,
        TestNetworkDAO.class
})
public class DAOTestSuite {
}

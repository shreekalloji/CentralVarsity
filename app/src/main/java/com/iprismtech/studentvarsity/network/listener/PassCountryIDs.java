package com.iprismtech.studentvarsity.network.listener;

import com.iprismtech.studentvarsity.dao.CountryDao;

import java.util.ArrayList;

public interface PassCountryIDs {

    void passCountry(ArrayList<CountryDao> countryDaos);
}

package com.db.ows.services;

import java.util.List;

import com.db.ows.model.AdverisementGroup;
import com.db.ows.model.Advertisement;

public interface AdvertisementService {
	public List<Advertisement> getAdvertisements();

	public Integer createAdvertisement(Advertisement advertisement, String userId);

	public List<Advertisement> getAdvertisements(String username);

	public List<Advertisement> getAdvertisementsForApprove();

	public boolean createAdvertisementGroup(String advertisementGroupName);

	public List<AdverisementGroup> getExistingGroups();
}

package com.db.ows.repositories;

import java.util.List;

import com.db.ows.model.AdverisementGroup;
import com.db.ows.model.Advertisement;

public interface AdvertisementRepository {
	public List<Advertisement> getAdvertisements();

	public Integer createAdvertisement(Advertisement advertisement, String userId);

	public List<Advertisement> getAdvertisements(String username);

	public List<Advertisement> getAdvertisementsForApprove();

	public boolean createAdvertisementGroup(String advertisementGroupName);

	public List<AdverisementGroup> getExistingGroups();
}

package com.ekos.insights;

import com.ekos.discovery.model.ApiInventory;
import com.ekos.discovery.model.DiscoveryResult;

import java.util.List;

public class ApiSearchService {

    public List<ApiInventory> searchByPath(
            DiscoveryResult result,
            String keyword) {

        return result.getStructure()
                .getApiInventory()
                .stream()
                .filter(api ->
                        api.getPath()
                                .toLowerCase()
                                .contains(keyword.toLowerCase()))
                .toList();

    }

    public List<ApiInventory> searchByController(
            DiscoveryResult result,
            String keyword) {

        return result.getStructure()
                .getApiInventory()
                .stream()
                .filter(api ->
                        api.getController()
                                .toLowerCase()
                                .contains(keyword.toLowerCase()))
                .toList();

    }

    public List<ApiInventory> searchByService(
            DiscoveryResult result,
            String keyword) {

        return result.getStructure()
                .getApiInventory()
                .stream()
                .filter(api ->
                        api.getService() != null &&
                                api.getService()
                                        .toLowerCase()
                                        .contains(keyword.toLowerCase()))
                .toList();

    }

}
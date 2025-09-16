/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.service.POHeader;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.RequestPOHeader;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponsePOHeader;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface POHeaderService {

    ResponseApi<List<ResponsePOHeader>> getAllPO();

    ResponseApi<ResponsePOHeader> getPOById(Integer id);

    ResponseApi<ResponsePOHeader> createPO(RequestPOHeader request);

    ResponseApi<ResponsePOHeader> updatePO(Integer id, RequestPOHeader request);

    ResponseApi<Void> deletePO(Integer id);
    
    ResponseApi<Void> deletePODetail(Integer detailId);
}

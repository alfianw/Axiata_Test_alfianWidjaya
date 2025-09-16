/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.service.POHeader;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.RequestPOHeader;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponsePODetail;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponsePOHeader;
import com.axiata.myboostTestMuhamadAlfianWidjaya.exception.ResourceNotFoundException;
import com.axiata.myboostTestMuhamadAlfianWidjaya.model.Item;
import com.axiata.myboostTestMuhamadAlfianWidjaya.model.PODetail;
import com.axiata.myboostTestMuhamadAlfianWidjaya.model.POHeader;
import com.axiata.myboostTestMuhamadAlfianWidjaya.repository.ItemRepository;
import com.axiata.myboostTestMuhamadAlfianWidjaya.repository.PODetailRepository;
import com.axiata.myboostTestMuhamadAlfianWidjaya.repository.POHeaderRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hp
 */
@Service
@Transactional
@RequiredArgsConstructor
public class POHeaderImpl implements POHeaderService {

    private final POHeaderRepository poHeaderRepository;
    private final PODetailRepository poDetailRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseApi<List<ResponsePOHeader>> getAllPO() {
        List<POHeader> poHeaders = poHeaderRepository.findAll();

        List<ResponsePOHeader> response = poHeaders.stream().map(po -> {

            ResponsePOHeader dto = new ResponsePOHeader();
            dto.setId(po.getId());
            dto.setDatetime(po.getDatetime());
            dto.setDescription(po.getDescription());
            dto.setTotalPrice(po.getDetails().stream().mapToInt(PODetail::getItemPrice).sum());
            dto.setTotalCost(po.getDetails().stream().mapToInt(PODetail::getItemCost).sum());
            dto.setCreatedBy(po.getCreatedBy());
            dto.setUpdatedBy(po.getUpdatedBy());
            dto.setCreatedDatetime(po.getCreatedDatetime());
            dto.setUpdatedDatetime(po.getUpdatedDatetime());

            //mapping manual untuk detail
            List<ResponsePODetail> details = po.getDetails().stream()
                    .map(detail -> {
                        ResponsePODetail d = new ResponsePODetail();
                        d.setId(detail.getId());
                        d.setPoId(detail.getPoHeader().getId().toString());
                        d.setItemId(detail.getItem().getId());
                        d.setItemQty(detail.getItemQty());
                        d.setItemCost(detail.getItemCost());
                        d.setItemPrice(detail.getItemPrice());
                        return d;
                    })
                    .collect(Collectors.toList());
            dto.setDetails(details);

            return dto;
        }).collect(Collectors.toList());

        return new ResponseApi<>("00", "Success", response);
    }

    @Override
    public ResponseApi<ResponsePOHeader> getPOById(Integer id) {
        POHeader poHeader = poHeaderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PO with id " + id + " not found"));

        ResponsePOHeader response = new ResponsePOHeader();
        response.setId(poHeader.getId());
        response.setDatetime(poHeader.getDatetime());
        response.setDescription(poHeader.getDescription());
        response.setTotalCost(poHeader.getTotalCost());
        response.setTotalPrice(poHeader.getTotalPrice());
        response.setCreatedBy(poHeader.getCreatedBy());
        response.setUpdatedBy(poHeader.getUpdatedBy());
        response.setCreatedDatetime(poHeader.getCreatedDatetime());
        response.setUpdatedDatetime(poHeader.getUpdatedDatetime());

        // Mapping manual untuk detail
        List<ResponsePODetail> details = poHeader.getDetails().stream()
                .map(detail -> {
                    ResponsePODetail d = new ResponsePODetail();
                    d.setId(detail.getId());
                    d.setPoId(detail.getPoHeader().getId().toString());
                    d.setItemId(detail.getItem().getId());
                    d.setItemQty(detail.getItemQty());
                    d.setItemCost(detail.getItemCost());
                    d.setItemPrice(detail.getItemPrice());
                    return d;
                }).collect(Collectors.toList());

        response.setDetails(details);

        return new ResponseApi<>("00", "Success", response);
    }

    @Override
    public ResponseApi<ResponsePOHeader> createPO(RequestPOHeader request) {
        POHeader poHeader = new POHeader();
        poHeader.setDescription(request.getDescription());
        poHeader.setCreatedBy(request.getCreatedBy());
        poHeader.setUpdatedBy(request.getUpdatedBy());

        POHeader savedHeader = poHeaderRepository.save(poHeader);

        // Membuat detail dari request
        PODetail detail = new PODetail();
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        detail.setPoHeader(savedHeader);
        detail.setItem(item);
        detail.setItemQty(request.getItemQty());
        detail.setItemCost(request.getItemCost());
        detail.setItemPrice(request.getItemPrice());

        poDetailRepository.save(detail);

        ResponsePOHeader response = modelMapper.map(savedHeader, ResponsePOHeader.class);
        return new ResponseApi<>("00", "PO Created Successfully", response);
    }

    @Override
    public ResponseApi<ResponsePOHeader> updatePO(Integer id, RequestPOHeader request) {
        POHeader poHeader = poHeaderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PO with id " + id + " not found"));

        // Update field header
        poHeader.setDescription(request.getDescription());
        poHeader.setUpdatedBy(request.getUpdatedBy());

        // Update detail
        PODetail detail = poHeader.getDetails().get(0);
        detail.setItem(itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item not found")));
        detail.setItemQty(request.getItemQty());
        detail.setItemCost(request.getItemCost());
        detail.setItemPrice(request.getItemPrice());

        poHeaderRepository.save(poHeader);

        // Mapping manual ke ResponsePOHeader
        ResponsePOHeader response = new ResponsePOHeader();
        response.setId(poHeader.getId());
        response.setDatetime(poHeader.getDatetime());
        response.setDescription(poHeader.getDescription());
        response.setTotalPrice(poHeader.getTotalPrice());
        response.setTotalCost(poHeader.getTotalCost());
        response.setCreatedBy(poHeader.getCreatedBy());
        response.setUpdatedBy(poHeader.getUpdatedBy());
        response.setCreatedDatetime(poHeader.getCreatedDatetime());
        response.setUpdatedDatetime(poHeader.getUpdatedDatetime());

        List<ResponsePODetail> details = poHeader.getDetails().stream().map(d -> {
            ResponsePODetail r = new ResponsePODetail();
            r.setId(d.getId());
            r.setPoId(poHeader.getId().toString());
            r.setItemId(d.getItem().getId());
            r.setItemQty(d.getItemQty());
            r.setItemCost(d.getItemCost());
            r.setItemPrice(d.getItemPrice());
            return r;
        }).collect(Collectors.toList());

        response.setDetails(details);

        return new ResponseApi<>("00", "PO Updated Successfully", response);
    }

    @Override
    public ResponseApi<Void> deletePO(Integer id) {
        POHeader poHeader = poHeaderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PO with id " + id + " not found"));
        poHeaderRepository.delete(poHeader);
        return new ResponseApi<>("00", "PO Deleted Successfully", null);
    }

    @Override
    public ResponseApi<Void> deletePODetail(Integer detailId) {
        // Cari PO Detail berdasarkan id
        PODetail poDetail = poDetailRepository.findById(detailId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "PO Detail with id " + detailId + " not found"));

        // Hapus PO Detail
        poDetailRepository.delete(poDetail);

        // Response sukses
        return new ResponseApi<>("00", "PO Detail Deleted Successfully", null);
    }
}

package com.adam.mobileserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.stereotype.Service;

import com.adam.mobileserver.dto.ItemDTO;
import com.adam.mobileserver.dto.PageRequestItemDTO;
import com.adam.mobileserver.dto.PageResponseItemDTO;
import com.adam.mobileserver.model.Item;
import com.adam.mobileserver.persistence.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;
	
	@Override
	public Long registerItem(ItemDTO dto) {
		Item item = dtoToEntity(dto);
		itemRepository.save(item);
		return item.getItemid();
	}

	@Override
	public ItemDTO getItem(ItemDTO dto) {
		Long itemid = dto.getItemid();
		Optional<Item> optional = itemRepository.findById(itemid);
		if(optional.isPresent()) {
			return entityToDto(optional.get());
		}
		return null;
	}

	@Override
	public Long updateItem(ItemDTO dto) {
		Item item = dtoToEntity(dto);
		Long itemid = item.getItemid();
		itemRepository.save(item);
		return itemid;
	}

	@Override
	public Long deleteItem(ItemDTO dto) {
		Item item = dtoToEntity(dto);
		Long itemid = item.getItemid();
		itemRepository.deleteById(itemid);
		return itemid;
	}

	@Override
	public PageResponseItemDTO getList(PageRequestItemDTO dto) {
		Sort sort = Sort.by("itemid").descending();
		Pageable pageable = PageRequest.of(
				dto.getPage()-1, dto.getSize(), sort);
		Page<Item> page = itemRepository.findAll(pageable);
		
		PageResponseItemDTO result = new PageResponseItemDTO();
		result.makePageList(pageable);
		result.setTotalPage(page.getTotalPages());
		List<Item> list = new ArrayList<>();
		page.get().forEach(item -> {
			list.add(item);
		});
		result.setItemList(list);
		return result;
	}

}

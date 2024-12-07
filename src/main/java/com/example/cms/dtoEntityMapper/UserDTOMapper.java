package com.example.cms.dtoEntityMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.cms.dto.UserDTO;
import com.example.cms.entity.Users;

@Component
public class UserDTOMapper {
	public UserDTO usersToDTO(Users user){
		   UserDTO userDTO = new UserDTO(); 
	        userDTO.setUserId(user.getUserId());
	        userDTO.setUserName(user.getUserName());
	        userDTO.setFirstName(user.getFirstName());
	        userDTO.setLastname(user.getLastName());
	        userDTO.setEmail(user.getEmail());
	        userDTO.setRole(user.getRole());
	        userDTO.setCreatedOn(user.getCreatedOn());

	        return userDTO;
	}
	
	public List<UserDTO> usersToDTOs(List<Users> usersList){
	        List<UserDTO> userDTOList = new ArrayList<>(); 
	  
	        for (Users user : usersList) {
	            UserDTO userDTO = new UserDTO(); 
	            userDTO.setUserId(user.getUserId());
	            userDTO.setUserName(user.getUserName());
	            userDTO.setFirstName(user.getFirstName());
	            userDTO.setLastname(user.getLastName());
	            userDTO.setEmail(user.getEmail());
	            userDTO.setRole(user.getRole());
	            userDTO.setCreatedOn(user.getCreatedOn());
	            userDTOList.add(userDTO);
	        }
	        return userDTOList; 
	}

}

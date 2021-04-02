package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CredentialService {
    private CredentialsMapper credentialsMapper;
    public CredentialService(CredentialsMapper credentialsMapper){
        this.credentialsMapper=credentialsMapper;
    }

    public List<CredentialForm> getCredentialsByUserId(Integer userId) {
        List<CredentialForm> credentials=credentialsMapper.getCredentialsWithUsername(userId);
        return credentials;
    }
    public int insert(CredentialForm credentialForm){
        return credentialsMapper.insert(credentialForm);
    }
    public int update(CredentialForm credentialForm){
        return credentialsMapper.update(credentialForm.getUrl(),credentialForm.getUserName(),credentialForm.getKey(),credentialForm.getPassword(),credentialForm.getCredentialId());
    }
    public int delete(int credentialId){
        return credentialsMapper.deleteCredential(credentialId);
    }
}

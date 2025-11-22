package com.task.stub.service;

import com.task.stub.model.request.PaymentRequestModel;
import com.task.stub.model.response.CheckResponseModel;
import com.task.stub.model.response.ContactModel;
import com.task.stub.model.response.DebtModel;
import com.task.stub.model.response.PaymentResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class MicroCreditService {

    public CheckResponseModel Check (int acc, int days)
    {
        CheckResponseModel model = new CheckResponseModel();
        model.setAccount(Integer.toString(acc));

        if((acc % 10) % 2 == 0)
        {
            model.setVip_client(false);
        }else model.setVip_client(true);

        model.setBlocked(false);
        model.setInn(Integer.toString(acc)+"111");
        List<DebtModel> DebtArray = new ArrayList<>();

        for(int i=0; i<days; i++)
        {
            DebtModel debtmodel = new DebtModel();
            debtmodel.setSum(new Random().nextInt(10000));
            debtmodel.setDescription("description");

            DebtArray.add(debtmodel);
        }
        model.setDebt(DebtArray);

        return model;
    }

    public PaymentResponseModel Confirm (PaymentRequestModel paymentRequestModel, int sumBank)
    {
        double temp = sumBank;
        sumBank = 0;
        while(temp > 0)
        {
            sumBank += temp % 10;
            temp /= 10;
        }
        PaymentResponseModel model = new PaymentResponseModel();
        model.setTransaction_id(paymentRequestModel.getTransaction_id());
        model.setBank_bik(Integer.toString(1000000000 + new Random().nextInt(1000000000)));
        model.setStatus("accepted");

        List<ContactModel> ContactModelArray = new ArrayList<>();
        ContactModel contactmodel = new ContactModel();
        contactmodel.setName("HL pay company");
        List<String> Telecom = new ArrayList<>();

        for(int i=0; i<sumBank; i++)
        {
            Telecom.add(GenetateStringTelecom());
        }
        contactmodel.setTelecom(Telecom);
        ContactModelArray.add(contactmodel);
        model.setContact(ContactModelArray);

        return model;

    }

    private String GenetateStringTelecom()
    {
        String Al = "0a1b2c3d4e5f6g7h8i9jklmnopqrstuvwxyz";

        Random random = new Random();
        char[] stringTelecom = new char[10];
        for(int i=0; i<10; i++)
            stringTelecom[i] = Al.charAt(random.nextInt(20));

        return new String(stringTelecom);
    }

}

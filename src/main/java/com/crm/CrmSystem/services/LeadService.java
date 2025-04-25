package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Leadsource;
import com.crm.CrmSystem.models.Lead;
import com.crm.CrmSystem.models.enums.SalesLeadStatus;
import com.crm.CrmSystem.repository.LeadsourceRepository;
import com.crm.CrmSystem.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    @Autowired
    LeadRepository leadRepository;

    @Autowired
    LeadsourceRepository leadsourceRepository;

    @Autowired
    SalesLeadService salesLeadService;

    //add leads from leadsource
//    public void addleads(int leadId){
//
//        System.out.println(l.get().getLeadName());
//     Lead l1 = new Lead();
//
//     l1.setLeadsource(l.get());
//     l1.setLeadStatus(LeadStatus.valueOf("NEW_LEAD"));
//        System.out.println(l1.getLeadStatus());
//     leadRepository.save(l1);
//    }
    public void addleads(int leadId) {
        Optional<Leadsource> l = leadsourceRepository.findById(leadId);
        if (l.isPresent()) {
            Leadsource leadsource = l.get();

            // ✅ Correct check: use Leadsource object, not ID
            Optional<Lead> existingLead = leadRepository.findByLeadsource(leadsource);

            if (existingLead.isPresent()) {
                System.out.println("Lead already exists for this Leadsource.");
                return; // Duplicate found, do not save
            }

//            if(timestamp.isEmpty() || timestamp ==null)
            String  timestamp = LocalDateTime.now().toString();


           LocalDateTime ls =  LocalDateTime.parse(timestamp);


            Lead l1 = new Lead();
            l1.setLeadsource(leadsource);
            l1.setLeadStatus(SalesLeadStatus.QUALIFIED);
            l1.setTimeStamp(ls);

            leadRepository.save(l1);
            System.out.println("Lead saved with status: " + l1.getLeadStatus() + l1.getTimeStamp());
        } else {
            throw new IllegalArgumentException("Leadsource with ID " + leadId + " not found");
        }
    }


    //change status of lead
        public boolean changeStatus(int id, String newStatus){
            if (leadRepository.existsById(id)){
                Lead l = leadRepository.findById(id).get();
                        l.setLeadStatus(SalesLeadStatus.valueOf(newStatus));
                if(newStatus.equals(SalesLeadStatus.QUALIFIED.toString())) {
                    if (!salesLeadService.existsInSalesLead(l)) {
                        salesLeadService.moveSingleLeadToSales(l);
                    }
                }
                System.out.println("Lead status changed: "+ id + newStatus);
                        leadRepository.save(l);
                return true;
            }
          return false;
        }

    //get all leadsources;
    public List<Leadsource> getall(){
        return leadsourceRepository.findAll();
    }

    //Get all leads
    public List<Lead> get(){
        return leadRepository.findAll();
    }

    //leadsources,leads,qualified leads,new leads;

    //total number of leadsource
    public long noOfleadsource(){
        return leadsourceRepository.count();
    }

    //total number of leads
    public long noOfLeads(){
        return leadRepository.count();
    }

        //total number of new Leads
        public int noOfNewLeads(){
            return (int) leadRepository.findAll().stream().filter(
                    lead -> lead.getLeadStatus() ==(SalesLeadStatus.NEW_LEAD)
            ).count();
        }

    public List<Leadsource> getAvailableLeadSources() {
        return leadsourceRepository.findAllUnusedLeadSources();
    }
}

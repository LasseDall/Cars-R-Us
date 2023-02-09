package semester3.car.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import semester3.car.dto.MemberRequest;
import semester3.car.dto.MemberResponse;
import semester3.car.entity.Member;
import semester3.car.repository.MemberRepository;

import java.util.List;

@Service
public class MemberService {

  MemberRepository memberRepository;


  public MemberService(MemberRepository memberRepository){
    this.memberRepository = memberRepository;
  }

  public ResponseEntity<Boolean> updateMember(String username, MemberRequest memberRequest) {
    Member updatedMember = memberRepository.findById(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

    //Member updatedMember = MemberRequest.getMemberEntity(memberRequest);
    updatedMember.setEmail(memberRequest.getEmail());
    updatedMember.setPassword(memberRequest.getPassword());
    updatedMember.setCity(memberRequest.getCity());
    updatedMember.setFirstName(memberRequest.getFirstName());
    updatedMember.setLastName(memberRequest.getLastName());
    updatedMember.setStreet(memberRequest.getStreet());
    updatedMember.setZip(memberRequest.getZip());
    memberRepository.save(updatedMember);
    return new ResponseEntity<Boolean>(true,HttpStatus.OK);
  }

  public void updateRanking(String username, int rankValue) {
    Member member = memberRepository.findById(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

    member.setRanking(rankValue);
    memberRepository.save(member);
  }

  public void deleteMember(String username) {
    Member member = memberRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    memberRepository.delete(member);
  }



  public MemberResponse addMember(MemberRequest memberRequest){
    //Later you should add error checks --> Missing arguments, email taken etc.

    Member newMember = MemberRequest.getMemberEntity(memberRequest);
    newMember = memberRepository.save(newMember);

    if(memberRepository.existsById(memberRequest.getUsername())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this ID already exist");
    }
    if(memberRepository.existsByEmail(memberRequest.getEmail())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this Email already exist");
    }

    return new MemberResponse(newMember, false);
  }


  public List<MemberResponse> getMembers(boolean includeAll) {

    List<Member> members = memberRepository.findAll();
        /*
        List<MemberResponse> memberResponses = new ArrayList<>();
        for(Member m : members){
            MemberResponse mr = new MemberResponse(m, includeAll);
            memberResponses.add(mr);
        }

        */
    List<MemberResponse>memberResponses = members.stream().map(m->new MemberResponse(m,includeAll)).toList();

    return memberResponses;
  }

  public MemberResponse findMemberByUsername(String username) {
    Member member = memberRepository.findById(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    return new MemberResponse(member, true);
  }
}


package com.incuventure.tests.queries;

import com.incuventure.cqrs.query.Finder;

import java.util.List;
import java.util.Map;

@Finder
public interface IEmployeeFinder {

    List<Map<String,?>> findAllEmployees();
//    List<Map<String,?>> findEmployeeByName(String name);

//    List<Map<String,?>> findAllBooks();
//
//    Integer findBookCountInLibrary();
//
//    Integer findCountOfMembersWhoHaveBorrowedBooks();
//
//    List<MemberDto> upcomingBirthDays();
//
//    List<Map<String,?>> findAllBooksWithMember(Long memberId);
//
//    Map<String,?> findBookWithId(Long bookId);
//
//    List<Map<String,?>> findBookLendings(Long memberId, Long bookId);
//
//    String findMostActiveBook();
//
//    MemberDto findMostActiveMember();


}

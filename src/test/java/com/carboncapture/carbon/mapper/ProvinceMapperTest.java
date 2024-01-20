package com.carboncapture.carbon.mapper;

import com.carboncapture.carbon.entity.Province;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.querydsl.QSort;

@SpringBootTest
class ProvinceMapperTest {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Test
    public void testSelectById(){
        System.out.println(provinceMapper.findById("1a2b3c4d5e6f7g8h9i0j1k2l3m4n5o6p7q8r"));
    }

    @Test
    public void testSave(){
        Province province = new Province();
        province.setCapital("东京");
        province.setRegionName("日本省");
        provinceMapper.save(province);
    }

    @Test
    public void testPaginate(){
//        PageRequestImpl pageRequest = new PageRequestImpl(1, 10);
        Page<Province> all = provinceMapper.findAll(QPageRequest.of(0, 10));
        System.out.println(all.getContent());
        System.out.println(all.getTotalPages());
        Sort.TypedSort<Province> sortEntityModel = QSort.sort(Province.class);
        // 按照创建时间升序排序
        Sort sortModel = sortEntityModel.by(Province::getCreateTime).ascending()
                // 按照省份名降序排序
                .and(sortEntityModel.by(Province::getRegionName).descending());
        Iterable<Province> result = provinceMapper.findAll(sortModel);
        result.forEach(System.out::println);
    }

//    @GetMapping("/jpatest7")
//    public List<PlayerEntity> jpatest7() {
//        Specification spec = (Specification) (root, criteriaQuery, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            Predicate countryPredicate = criteriaBuilder.equal(root.get("country"), "中国");
//            Predicate professionPredicate = criteriaBuilder.equal(root.get("profession"), "篮球");
//            Predicate goldenPredicate = criteriaBuilder.equal(root.get("goldenNum"), 5);
//            predicates.add(countryPredicate);
//            predicates.add(professionPredicate);
//            predicates.add(goldenPredicate);
//            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//        };
//        List<PlayerEntity> list = playerRepo.findAll(spec);
//        return list;
//    }
}
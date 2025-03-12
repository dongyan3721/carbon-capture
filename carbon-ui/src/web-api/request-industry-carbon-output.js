import request from "@/utils/request";

export function requestIndustryCarbonOutputByYear(year){
    return request({
        url: '/industry/byYear/'+year,
        method: 'GET'
    })
}
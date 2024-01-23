/**
 * @author Santa Antilles
 * @description 封装一些繁琐操作
 * @date 2023/12/28-10:50:07
 */

export function handleDataSubmitWithBlob(data){
    return new Blob([JSON.stringify(data)], {
        type: "application/json"
    })
}
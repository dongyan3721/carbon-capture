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

// ☆☆☆图片压缩☆☆☆输入为带头的BASE64字符串，导出png格式
export function compressImage(base64Input, height = 50, width = 50) {
    return new Promise((resolve, reject) => {
        const img = new Image();
        img.src = base64Input;

        img.onload = function() {
            const canvas = document.createElement('canvas');
            canvas.width = width;
            canvas.height = height;

            const ctx = canvas.getContext('2d');
            ctx.drawImage(img, 0, 0, width, height);

            let res = canvas.toDataURL('image/png')
            canvas.remove();

            resolve(res);
        };

        img.onerror = function() {
            reject("Error loading image");
        };
    });
}

// b64编码转换为原生二进制数据
export function base64ToBlob(base64String, contentType) {
    const byteCharacters = atob(base64String);
    const byteNumbers = new Array(byteCharacters.length);

    for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }

    const byteArray = new Uint8Array(byteNumbers);
    return new Blob([byteArray], { type: contentType });
}
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
    if(base64String.includes(',')){
        base64String = base64String.split(',')[1]
    }
    const byteCharacters = atob(base64String);
    const byteNumbers = new Array(byteCharacters.length);

    for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }

    const byteArray = new Uint8Array(byteNumbers);
    return new Blob([byteArray], { type: contentType });
}

// b64转file，文件类型默认给到png图片格式
export function base64ToFile(base64String, filename, mimeType = 'image/png') {
    if(base64String.includes(',')){
        base64String = base64String.split(',')[1]
    }
    const byteCharacters = atob(base64String);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += 512) {
        const slice = byteCharacters.slice(offset, offset + 512);
        const byteNumbers = new Array(slice.length);

        for (let i = 0; i < slice.length; i++) {
            byteNumbers[i] = slice.charCodeAt(i);
        }

        const byteArray = new Uint8Array(byteNumbers);
        byteArrays.push(byteArray);
    }

    const blob = new Blob(byteArrays, { type: mimeType });
    return new File([blob], filename, { type: mimeType });
}
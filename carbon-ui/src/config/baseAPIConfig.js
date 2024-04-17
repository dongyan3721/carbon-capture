const NOW_ENVIRONMENT = 'application'

// 访问后台静态资源，部署上线之后可能需要切换访问基路径
const baseStaticRecourseAPI = {
    develop: "http://localhost:8080",
    application: 'http://47.120.49.22:8080'
}

export {NOW_ENVIRONMENT, baseStaticRecourseAPI}
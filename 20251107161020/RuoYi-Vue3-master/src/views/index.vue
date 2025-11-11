<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>{{ hitokoto.hitokoto || '加载中...' }}</h2>
        <p v-if="hitokoto && hitokoto.from">
          —— {{ hitokoto.from }} {{ hitokoto.from_who ? `(${hitokoto.from_who})` : '' }}
        </p>
        <p>
          <el-button type="primary" @click="refreshHitokoto" size="small">刷新一言</el-button>
        </p>
        <p>
          <el-tag type="danger">&yen;免费开源</el-tag>
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>Github copilot</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>Github copilot</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row>
      <el-col :span="24" style="text-align: center;">
        <el-button type="primary" @click="refreshImage" size="small">刷新</el-button>
        <br />
        <img :src="imageUrl" alt="图床图片" style="max-width: 100%; height: auto; border: 1px solid #ddd; padding: 10px;" />
      </el-col>
    </el-row>
  </div>
</template>

<script setup name="Index">
import { ref, onMounted } from 'vue'

const version = ref('3.9.0')
const imageUrl = ref('https://t.alcy.cc/pc')
const hitokoto = ref({})

function goTarget(url) {
  window.open(url, '__blank')
}

function refreshImage() {
  imageUrl.value = 'https://t.alcy.cc/pc?t=' + Date.now()
}

async function fetchHitokoto() {
  try {
    const response = await fetch('https://v1.hitokoto.cn')
    const data = await response.json()
    hitokoto.value = data
  } catch (error) {
    console.error('获取一言失败:', error)
    hitokoto.value = { hitokoto: '若依后台管理框架', from: '', from_who: '' }
  }
}

function refreshHitokoto() {
  fetchHitokoto()
}

onMounted(() => {
  fetchHitokoto()
})
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>



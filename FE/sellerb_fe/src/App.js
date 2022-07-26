import "./App.css";
import { Routes, Route } from 'react-router-dom';

// import Main from './pages/Main/Main';
import { 
  Main,
  ManagerMain,
  ConsultantMain,
  ManagerMyPage,
  ManageRegister,
  NoticeList,
  NoticeWrite,
  NoticeDetail,
  NoticeEdit,
  ConsultantList,
} from './pages/index'

function App() {
  return(
  <>
    <Routes>
      <Route path="/" element={<Main /> } />
      <Route path="/manager/main" element={<ManagerMain /> } />
      <Route path="/consultant/main" element={<ConsultantMain />} />
      <Route path="/manager/mypage" element={<ManagerMyPage />} />
      <Route path="/manager/register" element={<ManageRegister />} />
      <Route path="/manager/noticeList" element={<NoticeList /> } />
      <Route path="/manager/noticeWrite" element={<NoticeWrite/>} />
      <Route path="/noticeDetail/:id" element={<NoticeDetail />} />
      <Route path="/manager/consultantList" element={<ConsultantList/>} />
      <Route path="/manager/noticeEdit" element={<NoticeEdit />} />
    </Routes>
  </>
  )
}

export default App;

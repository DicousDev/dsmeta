import axios from 'axios';
import { toast } from 'react-toastify';
import icon from '../../assets/img/notification-icon.svg';
import { BASE_URL } from '../../utils/request';
import './styles.css';

type Props = {
    idVenda: number;
}

function NotificationButton({ idVenda } : Props) {

    function handleClick(idVenda: number) {
        axios.get(`${BASE_URL}/sales/${idVenda}/notification`)
            .then(response => {
                toast.info("SMS Enviado com Sucesso!");
            }
        );
    }

    return (
        <div className="dsmeta-red-btn" onClick={() => handleClick(idVenda)}>
            <img src={icon} alt="Notificar" />
        </div>
    );
}
  
export default NotificationButton;
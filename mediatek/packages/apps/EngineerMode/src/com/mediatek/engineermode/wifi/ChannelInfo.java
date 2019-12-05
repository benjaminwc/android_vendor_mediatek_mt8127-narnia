/* Copyright Statement:
 *
 * This software/firmware and related documentation ("MediaTek Software") are
 * protected under relevant copyright laws. The information contained herein
 * is confidential and proprietary to MediaTek Inc. and/or its licensors.
 * Without the prior written permission of MediaTek inc. and/or its licensors,
 * any reproduction, modification, use or disclosure of MediaTek Software,
 * and information contained herein, in whole or in part, shall be strictly prohibited.
 */
/* MediaTek Inc. (C) 2010. All rights reserved.
 *
 * BY OPENING THIS FILE, RECEIVER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES
 * THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE")
 * RECEIVED FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO RECEIVER ON
 * AN "AS-IS" BASIS ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL WARRANTIES,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NONINFRINGEMENT.
 * NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY WHATSOEVER WITH RESPECT TO THE
 * SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY, INCORPORATED IN, OR
 * SUPPLIED WITH THE MEDIATEK SOFTWARE, AND RECEIVER AGREES TO LOOK ONLY TO SUCH
 * THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO. RECEIVER EXPRESSLY ACKNOWLEDGES
 * THAT IT IS RECEIVER'S SOLE RESPONSIBILITY TO OBTAIN FROM ANY THIRD PARTY ALL PROPER LICENSES
 * CONTAINED IN MEDIATEK SOFTWARE. MEDIATEK SHALL ALSO NOT BE RESPONSIBLE FOR ANY MEDIATEK
 * SOFTWARE RELEASES MADE TO RECEIVER'S SPECIFICATION OR TO CONFORM TO A PARTICULAR
 * STANDARD OR OPEN FORUM. RECEIVER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S ENTIRE AND
 * CUMULATIVE LIABILITY WITH RESPECT TO THE MEDIATEK SOFTWARE RELEASED HEREUNDER WILL BE,
 * AT MEDIATEK'S OPTION, TO REVISE OR REPLACE THE MEDIATEK SOFTWARE AT ISSUE,
 * OR REFUND ANY SOFTWARE LICENSE FEES OR SERVICE CHARGE PAID BY RECEIVER TO
 * MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE.
 *
 * The following software/firmware and/or related documentation ("MediaTek Software")
 * have been modified by MediaTek Inc. All revisions are subject to any receiver's
 * applicable license agreements with MediaTek Inc.
 */

package com.mediatek.engineermode.wifi;

import android.widget.ArrayAdapter;

import com.mediatek.engineermode.Elog;
import com.mediatek.xlog.Xlog;

public class ChannelInfo {
    private static final String TAG = "EM/Wifi_ChannelInfo";
    public static final int CHANNEL_NUMBER_14 = 14;
    private static final int DEFAULT_CHANNEL_COUNT = 11;
    private static final int MAX_CHANNEL_COUNT = 75;
    protected String mChannelSelect = null;
    protected static long[] sChannels = null;
    protected static boolean sHas14Ch = false;
    protected static boolean sHasUpper14Ch = false;
    final String[] mChannelName = {
            // 2.4GHz frequency serials
            "Channel 1 [2412MHz]", "Channel 2 [2417MHz]",
            "Channel 3 [2422MHz]", "Channel 4 [2427MHz]",
            "Channel 5 [2432MHz]", "Channel 6 [2437MHz]",
            "Channel 7 [2442MHz]", "Channel 8 [2447MHz]",
            "Channel 9 [2452MHz]", "Channel 10 [2457MHz]",
            "Channel 11 [2462MHz]", "Channel 12 [2467MHz]",
            "Channel 13 [2472MHz]", "Channel 14 [2484MHz]", };

    final String[] mFullChannelName = {
            // 2.4GHz frequency serials
            "Channel 1 [2412MHz]", "Channel 2 [2417MHz]",
            "Channel 3 [2422MHz]", "Channel 4 [2427MHz]",
            "Channel 5 [2432MHz]", "Channel 6 [2437MHz]",
            "Channel 7 [2442MHz]", "Channel 8 [2447MHz]",
            "Channel 9 [2452MHz]", "Channel 10 [2457MHz]",
            "Channel 11 [2462MHz]", "Channel 12 [2467MHz]",
            "Channel 13 [2472MHz]", "Channel 14 [2484MHz]",
            // 5GHz frequency serials
            "Channel 36 [5180MHz]", "Channel 38 [5190MHz]",
            "Channel 40 [5200MHz]", "Channel 44 [5220MHz]", 
            "Channel 46 [5230MHz]", "Channel 48 [5240MHz]",
            "Channel 52 [5260MHz]", "Channel 54 [5270MHz]",
            "Channel 56 [5280MHz]", "Channel 60 [5300MHz]", 
            "Channel 62 [5310MHz]", "Channel 64 [5320MHz]",
            "Channel 68 [5340MHz]", "Channel 70 [5350MHz]",
            "Channel 72 [5360MHz]", "Channel 76 [5380MHz]",
            "Channel 78 [5390MHz]", "Channel 80 [5400MHz]",
            "Channel 84 [5420MHz]", "Channel 86 [5430MHz]",
            "Channel 88 [5440MHz]", "Channel 92 [5460MHz]",
            "Channel 94 [5470MHz]", "Channel 96 [5480MHz]",
            "Channel 100 [5500MHz]", "Channel 102 [5510MHz]",
            "Channel 104 [5520MHz]", "Channel 108 [5540MHz]", 
            "Channel 110 [5550MHz]", "Channel 112 [5560MHz]",
            "Channel 116 [5580MHz]", "Channel 118 [5590MHz]", 
            "Channel 120 [5600MHz]", "Channel 124 [5620MHz]", 
            "Channel 126 [5630MHz]", "Channel 128 [5640MHz]",
            "Channel 132 [5660MHz]", "Channel 134 [5670MHz]", 
            "Channel 136 [5680MHz]", "Channel 140 [5700MHz]", 
            "Channel 149 [5745MHz]", "Channel 151 [5755MHz]", 
            "Channel 153 [5765MHz]", "Channel 157 [5785MHz]", 
            "Channel 159 [5795MHz]", "Channel 161 [5805MHz]", 
            "Channel 165 [5825MHz]", "Channel 167 [5835MHz]",
            "Channel 169 [5845MHz]", "Channel 173 [5865MHz]", 
            "Channel 175 [5875MHz]", "Channel 177 [5885MHz]", 
            "Channel 181 [5905MHz]", "Channel 183 [4915MHz]", 
            "Channel 184 [4920MHz]", "Channel 185 [4925MHz]", 
            "Channel 187 [4935MHz]", "Channel 188 [4940MHz]", 
            "Channel 189 [4945MHz]", "Channel 192 [4960MHz]", 
            "Channel 196 [4980MHz]" };
    final int[] mChannelFreq = {
            // 2.4GHz frequency serials' channels
            2412000, 2417000, 2422000, 2427000, 2432000, 2437000, 2442000,
            2447000, 2452000, 2457000, 2462000, 2467000, 2472000, 2484000,
            // 5GHz frequency serials' channels
            5180000, 5190000, 5200000, 5220000, 5230000, 5240000, 5260000, 5270000, 5280000, 5300000,
            5310000, 5320000, 5340000, 5350000, 5360000, 5380000, 5390000, 5400000, 5420000, 5430000,
            5440000, 5460000, 5470000, 5480000, 5500000, 5510000, 5520000, 5540000, 5550000, 5560000, 
            5580000, 5590000, 5600000, 5620000, 5630000, 5640000, 5660000, 5670000, 5680000, 5700000, 
            5745000, 5755000, 5765000, 5785000, 5795000, 5805000, 5825000, 5835000, 5845000, 5865000, 
            5875000, 5885000, 5905000, 4915000, 4920000, 4925000, 4935000, 4940000, 4945000, 4960000,
            4980000 };
    
    private final Object[][] sBw40MChannels = {
        {38, 5190000, "Channel 38 [5190MHz]"}, 
        {46, 5230000, "Channel 46 [5230MHz]"}, 
        {54, 5270000, "Channel 54 [5270MHz]"}, 
        {62, 5310000, "Channel 62 [5310MHz]"}, 
        {70, 5350000, "Channel 70 [5350MHz]"}, 
        {78, 5390000, "Channel 78 [5390MHz]"}, 
        {86, 5430000, "Channel 86 [5430MHz]"}, 
        {94, 5470000, "Channel 94 [5470MHz]"}, 
        {102, 5510000, "Channel 102 [5510MHz]"}, 
        {110, 5550000, "Channel 110 [5550MHz]"}, 
        {118, 5590000, "Channel 118 [5590MHz]"},
        {126, 5630000, "Channel 126 [5630MHz]"},
        {134, 5670000, "Channel 134 [5670MHz]"}, 
        {151, 5755000, "Channel 151 [5755MHz]"}, 
        {159, 5795000, "Channel 159 [5795MHz]"}, 
        {167, 5835000, "Channel 167 [5835MHz]"},
        {175, 5875000, "Channel 175 [5875MHz]"},
    };
    
    private static final int[] sBw40mUnsupported2dot4GChannels = {1, 2, 12, 13, 14};
    
    public void removeBw40mUnsupported2dot4GChannels(ArrayAdapter<String> adapter) {
        for (int i = 0; i < sBw40mUnsupported2dot4GChannels.length; i++) {
            int channel = sBw40mUnsupported2dot4GChannels[i];
            int index = channel - 1;
            adapter.remove(mFullChannelName[index]);
        }
    } 
    
    public void insertBw40mUnsupported2dot4GChannels(ArrayAdapter<String> adapter) {
        for (int i = 0; i < sBw40mUnsupported2dot4GChannels.length; i++) {
            int channel = sBw40mUnsupported2dot4GChannels[i];
            String channelName = mFullChannelName[channel - 1];
            if (isContains(channel)) {
                insertChannelIntoAdapterByOrder(adapter, channelName);
            }
        }
    }
    
    private String getChannelNameIntern(int index, Object[][] datas) {
        String name = (String)datas[index][2];
        return name;
    }
    
    private int getChannelIdIntern(int index , Object[][] datas) {
        Integer id = (Integer)datas[index][0];
        return id.intValue();
    }
    
    public void removeBw40MChannelsFromAdapter(ArrayAdapter<String> adapter) {
        for (int i = 0; i < sBw40MChannels.length; i++) {
            adapter.remove(getChannelNameIntern(i, sBw40MChannels));
        }
    }
    
    public void remove5GChannelsFromAdapter(ArrayAdapter<String> adapter) {
        for (int i = adapter.getCount() - 1; i > 0; i--) {
            String name = adapter.getItem(i);
            int id = extractChannelIdFromName(name);
            if (id > CHANNEL_NUMBER_14) {
                adapter.remove(name);
            }
        }
    }
    
    public void insert5GChannelsIntoAdapter(ArrayAdapter<String> adapter) {
        for (int i = 1; i <= sChannels[0]; i++) {
            long id = sChannels[i];
            if (id > CHANNEL_NUMBER_14) {
                String tag = getChannelNameById(id);
                if (tag != null) {
                    insertChannelIntoAdapterByOrder(adapter, tag);
                } else {
                    Elog.d(TAG, "UNKnown channel:" + id);
                }
            }
        }
    }
    
    public void insertBw40MChannelsIntoAdapter(ArrayAdapter<String> adapter) {
        for (int i = 0; i < sBw40MChannels.length; i++) {
            int id = getChannelIdIntern(i, sBw40MChannels);
            if (isChannelSupported(id)) {
                String name = getChannelNameIntern(i, sBw40MChannels);
                insertChannelIntoAdapterByOrder(adapter, name);
            }
        }
    }
    
    private int computeInsertIndex(ArrayAdapter<String> adapter, int channel) {
        int targetIndex = -1;
        for (int i = 0; i < adapter.getCount(); i++) {
            String name = adapter.getItem(i);
            int id = extractChannelIdFromName(name);
            if (id > channel) {
                targetIndex = i;
                break;
            }
        }
        if(targetIndex == -1) {
            targetIndex = adapter.getCount();
        } 
        return targetIndex;
    }
    
    public String getChannelNameById(long channel) {
        for (String name : mFullChannelName) {
            int id = extractChannelIdFromName(name);
            if (id == channel) {
                return name;
            }
        }
        return null;
    }
    
    private int extractChannelIdFromName(String fullName) {
        int id = -1;
        String[] strs = fullName.split(" +");
        if (strs.length == 3) {
            try {
                id = Integer.valueOf(strs[1]);
            } catch (NumberFormatException e) {
                Elog.d(TAG, "NumberFormatException:" + e.getMessage());
            }
        } else {
            Elog.d(TAG, "extractChannelIdFromName(): " + fullName + " invalid name format!");
        }
        return id;
    }
    
    public void insertChannelIntoAdapterByOrder(ArrayAdapter<String> adapter, String channelName) {
        if (adapter.getPosition(channelName) == -1) {
            int id = extractChannelIdFromName(channelName);
            int targetIndex = computeInsertIndex(adapter, id);
            adapter.insert(channelName, targetIndex);
        }
    }
    
    private boolean isChannelSupported(long channel) {
        boolean supported = false;
        for (int i = 1; i <= sChannels[0]; i++) {
            long ch = sChannels[i];
            if ((ch == channel) || ((ch + 2) == channel) || ((ch - 2) == channel)) {
                supported = true;
                break;
            }
            if (ch > channel) {
                break;
            }
        }
        Elog.d(TAG, "isChannelSupported: channel:" + channel + " supported:" + supported);
        return supported;
    }
    /**
     * Get channel frequency
     * 
     * @return Channel frequency
     */
    public int getChannelFreq() {
        return mChannelFreq[getChannelIndex()];
    }

    /**
     * Get index in the channel array by Channel name
     * 
     * @return The channel index
     */
    public int getChannelIndex() {
        for (int i = 0; i < mFullChannelName.length; i++) {
            if (mFullChannelName[i].equals(mChannelSelect)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Check the channel is support or not in the phone
     * 
     * @param channel
     *            Need to check the channel's number
     * @return True if phone support the channel, or false
     */
    public boolean isContains(int channel) {
        for (int i = 1; i <= sChannels[0]; i++) {
            if (channel == sChannels[i]) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get WiFi chip support channels
     */
    public static void getSupportChannels() {
        sChannels = new long[MAX_CHANNEL_COUNT];
        if (EMWifi.sIsInitialed) {
            if (0 == EMWifi.getSupportChannelList(sChannels)) {
                Xlog.v(TAG, "LENGTH channels[0] = " + sChannels[0]);
                for (int i = 1; i <= sChannels[0]; i++) {
                    if (CHANNEL_NUMBER_14 == sChannels[i]) {
                        sHas14Ch = true;
                    }
                    if (sChannels[i] > CHANNEL_NUMBER_14) {
                        sHasUpper14Ch = true;
                    }
                    Xlog.v(TAG, "channels[" + (i) + "] = " + sChannels[i]);
                }
            } else {
                sChannels[0] = DEFAULT_CHANNEL_COUNT;
                for (int i = 0; i < DEFAULT_CHANNEL_COUNT; i++) {
                    sChannels[i + 1] = i + 1;
                }
            }
        } else {
            Xlog.v(TAG, "Wifi is not initialed");
        }
    }

    /**
     * Constructor
     */
    public ChannelInfo() {
        mChannelSelect = mFullChannelName[0];
    }
}
